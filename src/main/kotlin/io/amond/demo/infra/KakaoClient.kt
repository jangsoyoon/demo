package io.amond.demo.infra

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class KakaoClient(
    private val webClient: WebClient
) {
    // TODO: Implement Kakao API calls here
}
