package io.amond.demo.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AuthRouter {
    val basePath = "/api/auth"

    @Bean
    fun authRoute(handler: AuthHandler) = coRouter {
        path(basePath).nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("", handler::hello)
            }
        }
    }
}