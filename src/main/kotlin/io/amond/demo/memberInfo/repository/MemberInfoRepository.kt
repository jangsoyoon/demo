package io.amond.demo.memberInfo.repository

import io.amond.demo.memberInfo.MemberInfo
import io.amond.demo.support.ActiveRepository

interface MemberInfoRepository: ActiveRepository<MemberInfo, Long> {
    fun findByFirebaseUidAndActive(firebaseUid: String, active: Boolean): MemberInfo?
}