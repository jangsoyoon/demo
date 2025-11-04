package io.amond.demo.fruit.repository

import io.amond.demo.fruit.Fruit
import org.springframework.data.jpa.repository.JpaRepository

interface FruitRepository: JpaRepository<Fruit, Long> {
    fun findByIdAndActive(id: Long, active: Boolean): Fruit?
}