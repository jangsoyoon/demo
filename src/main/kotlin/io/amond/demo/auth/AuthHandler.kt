package io.amond.demo.auth

import io.amond.demo.fruit.service.FruitService
import io.amond.demo.fruit.view.FruitData
import io.amond.demo.fruit.view.UpdateFruitReq
import io.amond.demo.history.ActionType
import io.amond.demo.history.MethodType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitPrincipal
import org.springframework.web.reactive.function.server.bodyValueAndAwait


@Component
class AuthHandler(
) {
    suspend fun hello(request: ServerRequest): ServerResponse {
        val principal = request.awaitPrincipal() as JwtAuthenticationToken
//        val uid = principal.token.claims["sub"]

        val uid = request.awaitPrincipal()!!.name

        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(mapOf("message" to "Hello, Firebase User!", "uid" to uid))
    }
}