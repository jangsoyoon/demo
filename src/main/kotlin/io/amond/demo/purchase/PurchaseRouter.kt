package io.amond.demo.purchase

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter
import java.awt.PageAttributes

@Configuration
class PurchaseRouter {
    val basePath = "/api/v1/purchase"
    @Bean
    fun purchaseRoute(purchaseHandler: PurchaseHandler) = coRouter {
        path(basePath).nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("", purchaseHandler::purchase)
            }
        }
    }
}