package io.amond.demo.fruit.service

import com.fasterxml.jackson.module.kotlin.convertValue
import io.amond.demo.fruit.repository.FruitRepository
import io.amond.demo.fruit.Fruit
import io.amond.demo.fruit.repository.AdminUpdateHistoryRepository
import io.amond.demo.fruit.view.FruitData
import io.amond.demo.fruit.view.UpdateFruitReq
import io.amond.demo.history.ActionType
import io.amond.demo.history.AdminUpdateHistory
import io.amond.demo.history.MethodType
import io.amond.demo.config.AppContext
import jakarta.persistence.EntityManager
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

import jakarta.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository

@Service
class FruitService(
    private val fruitRepository: FruitRepository,
    private val jdbcTemplate: JdbcTemplate,
    private val adminUpdateHistoryRepository: AdminUpdateHistoryRepository,
    private val appContext: AppContext,
    private val em: EntityManager,
    ) {

    @Transactional
    fun create(fruits: List<FruitData>): List<Long>{
        val fruitList: MutableList<Fruit> = mutableListOf()

        for(fruit in fruits){
            fruitList.add(Fruit(
                fruit.name,
                fruit.price
            ))
        }

        bulkFruitSave(fruitList)
        val findFruits = fruitRepository.findAll()
        return findFruits.map { it.id!! }
    }


    @Transactional
    fun update(updateFruitReq: UpdateFruitReq, method: MethodType, endPoint: String, actionType: ActionType) {
        val now = Instant.now()
        val requestPayload =
            appContext.objectMapper().convertValue<MutableMap<String, Any>>(updateFruitReq)

        val findFruit = fruitRepository.findByIdAndActive(updateFruitReq.id, true)
        if(findFruit != null) {
        val beforeData = appContext.objectMapper().convertValue<MutableMap<String, Any>>(findFruit)
            updateFruitReq.name?.let { findFruit.name = it }
            updateFruitReq.price?.let { findFruit.price = it }

            findFruit.apply {
                updatedAt = now
                updatedId = 1
            }

         fruitRepository.save(findFruit)
            val tableName = getTableName(fruitRepository)
            val newFruit = fruitRepository.findByIdAndActive(findFruit.id!!, true)
            val afterData = appContext.objectMapper().convertValue<MutableMap<String, Any>>(findFruit)
            adminUpdateHistoryRepository.save(
                AdminUpdateHistory(
                    tableName = tableName,
                    targetId = findFruit.id!!,
                    endPoint = endPoint,
                    method = method,
                    actionType = actionType,
                    beforeData = beforeData,
                    afterData = afterData,
                    requestPayload = requestPayload
                )
            ).apply {
                createdAt = now
                createdId = 1
            }
        }

    }
    /**
     * Repository 인터페이스로부터 테이블명을 추출합니다.
     */
    fun getTableName(repository: JpaRepository<*, *>): String {
        val entityClass = getEntityClass(repository)

        val tableAnnotation = entityClass.getAnnotation(Table::class.java)
        if (tableAnnotation != null && tableAnnotation.name.isNotBlank()) {
            return tableAnnotation.name
        }

        // Spring Boot의 기본 실제 명명 전략(camelCase to snake_case)을 적용합니다.
        return camelToSnake(entityClass.simpleName)
    }

    private fun camelToSnake(str: String): String {
        val pattern = "(?<=.)[A-Z]".toRegex()
        return str.replace(pattern, "_$0").lowercase()
    }

    private fun getEntityClass(repository: JpaRepository<*, *>): Class<*> {
        val queue = java.util.ArrayDeque<Class<*>>()
        queue.addAll(repository.javaClass.interfaces)
        val visited = mutableSetOf<Class<*>>()

        while(queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current in visited) continue
            visited.add(current)

            for (genericInterface in current.genericInterfaces) {
                if (genericInterface is java.lang.reflect.ParameterizedType) {
                    val rawType = genericInterface.rawType
                    if (rawType == JpaRepository::class.java) {
                        val typeArg = genericInterface.actualTypeArguments[0]
                        if (typeArg is Class<*>) {
                            return typeArg
                        }
                    }
                }
            }
            queue.addAll(current.interfaces)
        }

        throw IllegalArgumentException("Cannot extract entity class from repository: ${repository.javaClass.name}")
    }

    private fun bulkFruitSave(
        fruitList: MutableList<Fruit>,
    ) {
        val now = Instant.now()
        val size = 1000
        jdbcTemplate.batchUpdate(
            """INSERT INTO fruit (name, price)
                    VALUES (?, ?)""".trimMargin(),
            fruitList,
            size
        ) { ps, fruit ->
            ps.setString(2, fruit.name)
            ps.setLong(3, fruit.price)
        }
    }
}