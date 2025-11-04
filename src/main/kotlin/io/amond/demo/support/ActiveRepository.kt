package io.amond.demo.support

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface ActiveRepository<T, ID> : JpaRepository<T, ID> {

    fun findByIdAndActive(id: Long, active: Boolean): T?

    fun findAllByActive(active: Boolean, pageable: Pageable): Page<T>

    fun findAllByActive(active: Boolean): List<T>?

}
