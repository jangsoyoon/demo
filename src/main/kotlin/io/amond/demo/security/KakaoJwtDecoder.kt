package io.amond.demo.security

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class KakaoJwtDecoder: ReactiveJwtDecoder {
    override fun decode(token: String): Mono<Jwt> {
        // TODO: Implement Kakao token decoding
        return Mono.empty()
    }
}
