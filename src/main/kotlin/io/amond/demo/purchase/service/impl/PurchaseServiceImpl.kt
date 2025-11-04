package io.amond.demo.purchase.service.impl

import io.amond.demo.memberInfo.repository.MemberInfoRepository
import io.amond.demo.purchase.service.PurchaseService
import io.amond.demo.purchase.view.PurchaseReq
import io.amond.demo.purchase.view.PurchaseRes
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class PurchaseServiceImpl(
    private val memberInfoRepository: MemberInfoRepository,
) : PurchaseService {
    override fun purchase(firebaseUid: String, purchaseReq: PurchaseReq): PurchaseRes {
        val memberInfo = memberInfoRepository.findByFirebaseUidAndActive(firebaseUid, true)
            ?: throw EntityNotFoundException("not found a member with firebaseUid: $firebaseUid")

        return PurchaseRes(
            id = 2
        )
    }
}