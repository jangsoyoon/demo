package io.amond.demo.memberInfo

import io.amond.demo.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class MemberInfo (
    var firebaseUid: String,
    var email: String? = null,
    var nickname: String? = null,
): BaseEntity()