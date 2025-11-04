package io.amond.demo.fruit.view

data class FruitData(
    var id: Long,
    var name: String,
    var price: Long,
)

data class UpdateFruitReq(
    var id: Long,
    var name: String? = null,
    var price: Long? = null,

)