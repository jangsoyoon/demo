package io.amond.demo.fruit

import io.amond.demo.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class Fruit (
    var name: String,
    var price: Long,
): BaseEntity()