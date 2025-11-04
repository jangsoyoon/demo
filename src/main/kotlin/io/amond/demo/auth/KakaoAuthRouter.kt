package io.amond.demo.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class KakaoAuthRouter(
    private val kakaoAuthHandler: KakaoAuthHandler
) {

    @Bean
    fun kakaoAuthRoutes() = coRouter {
        "/login/kakao".nest {
            GET("", kakaoAuthHandler::login)
        }
    }
}
