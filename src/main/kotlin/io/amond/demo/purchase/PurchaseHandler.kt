package io.amond.demo.purchase

import io.amond.demo.purchase.view.PurchaseReq
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class PurchaseHandler {
    suspend fun purchase(request: ServerRequest): ServerResponse {
        val firebaseUid = request.awaitPrincipal()!!.name
        val purchaseReq = request.awaitBody<PurchaseReq>()

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait("ok")
    }
}