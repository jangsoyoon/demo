package io.amond.demo.test

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait


@Component
class TestHandler(
) {
    suspend fun test(request: ServerRequest): ServerResponse {
        val responseMessage = "Hello World"
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(responseMessage)
    }
}