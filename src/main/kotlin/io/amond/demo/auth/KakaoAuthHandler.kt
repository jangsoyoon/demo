package io.amond.demo.auth

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class KakaoAuthHandler {
    suspend fun login(request: ServerRequest): ServerResponse {
        // TODO: Implement Kakao login logic
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait("")
    }
}
