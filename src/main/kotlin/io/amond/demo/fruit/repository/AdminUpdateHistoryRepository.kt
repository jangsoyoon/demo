package io.amond.demo.fruit.repository

import io.amond.demo.fruit.Fruit
import io.amond.demo.history.AdminUpdateHistory
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AdminUpdateHistoryRepository: JpaRepository<AdminUpdateHistory, Long> {
}