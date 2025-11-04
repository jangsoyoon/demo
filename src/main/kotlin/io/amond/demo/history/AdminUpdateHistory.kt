package io.amond.demo.history

import com.fasterxml.jackson.databind.ser.Serializers.Base
import jakarta.persistence.*
import org.hibernate.annotations.Type
import com.vladmihalcea.hibernate.type.json.JsonType
import io.amond.demo.BaseEntity
import java.time.Instant


@Entity
@Table
class AdminUpdateHistory(
    var tableName: String,
    var targetId: Long,
    var endPoint: String,
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    var method: MethodType,
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    var actionType: ActionType,
    @Column(columnDefinition = "json")
    @Type(JsonType::class)
    val beforeData: MutableMap<String, Any>? = null,
    @Column(columnDefinition = "json")
    @Type(JsonType::class)
    val afterData: MutableMap<String, Any>? = null,
    @Column(columnDefinition = "json")
    @Type(JsonType::class)
    val requestPayload: MutableMap<String, Any>? = null,
): BaseEntity()

enum class MethodType {
    GET, POST, PUT, DELETE
}

enum class ActionType {
    UPDATE, DELETE
}