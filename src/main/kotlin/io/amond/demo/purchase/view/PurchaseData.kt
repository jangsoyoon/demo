package io.amond.demo.purchase.view

import java.math.BigDecimal

data class PurchaseReq(
    val fruitId: Long,
    val price: BigDecimal,

)

data class PurchaseRes(
    val id: Long,
)