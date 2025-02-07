package io.amond.demo.test

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class TestRouter {
    val basePath = "/hello"

    @Bean
    fun testRoute(handler: TestHandler) = coRouter {
        path(basePath).nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("", handler::test)
            }
        }
    }
}