package io.amond.demo.purchase.service

import io.amond.demo.purchase.view.PurchaseReq
import io.amond.demo.purchase.view.PurchaseRes

interface PurchaseService {
    fun purchase(firebaseUid: String, purchaseReq: PurchaseReq): PurchaseRes
}