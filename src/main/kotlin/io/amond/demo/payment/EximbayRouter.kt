package io.amond.demo.payment

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class EximbayRouter {
    val basePath = "/api/v1/eximbay"

    @Bean
    fun eximbayRoute(eximbayHandler: EximbayHandler) =coRouter {
        path(basePath).nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("/status", eximbayHandler::eximbayStatus)
            }
        }
    }
}