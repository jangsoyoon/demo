package io.amond.demo.test

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
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait


@Component
class TestHandler(
    private val fruitService: FruitService,
) {
    suspend fun test(request: ServerRequest): ServerResponse {
        val responseMessage = "Hello World"
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(responseMessage)
    }


    suspend fun create(request: ServerRequest): ServerResponse {
        val fruits = request.awaitBody<List<FruitData>>()
        return withContext(Dispatchers.IO) {
            fruitService.create(fruits)
        }.let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)

        }
    }

    suspend fun update(request: ServerRequest): ServerResponse {
        val updateFruitReq = request.awaitBody<UpdateFruitReq>()

        val method = MethodType.valueOf(request.method().name())
        val endpoint = request.path()
        val actionType = ActionType.UPDATE
        return withContext(Dispatchers.IO) {
            fruitService.update(updateFruitReq, method, endpoint, actionType)
        }.let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)

        }
    }
}