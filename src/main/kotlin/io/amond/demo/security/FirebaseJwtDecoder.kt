package io.amond.demo.security

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.nimbusds.jwt.JWTParser
import mu.KotlinLogging
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

private val log = KotlinLogging.logger {}

@Component
class FirebaseJwtDecoder(private val firebaseAuth: FirebaseAuth) : ReactiveJwtDecoder {
    companion object {
        const val FIREBASE_VERIFY_ID_TOKEN_EXCEPTION_MESSAGE = "verify firebase token failed"
    }

    override fun decode(token: String): Mono<Jwt> {
        log.debug { "]-----] FirebaseJwtDecoder::decode [-----[ $token" }
        val jwtBuilder = Jwt.withTokenValue(token)
        val parse = JWTParser.parse(token)
        log.debug { "]-----] FirebaseJwtDecoder::decode parse [-----[ $parse" }
        try {
            jwtBuilder.headers {
                it["alg"] = parse.header.algorithm.name
                it["typ"] = parse.header.type.type
            }
                .issuedAt(parse.jwtClaimsSet.issueTime.toInstant())
                .expiresAt(parse.jwtClaimsSet.expirationTime.toInstant())
            log.debug { "]-----] FirebaseJwtDecoder::decode jwtBuilder [-----[ ${jwtBuilder.build().headers}" }

            val verifyIdToken = firebaseAuth.verifyIdToken(token)
            log.debug { "]-----] FirebaseJwtDecoder::decode verifyIdToken [-----[ $verifyIdToken" }
            log.debug { "]-----] FirebaseJwtDecoder::decode uid [-----[ ${verifyIdToken.uid}" }
            jwtBuilder.claims {
                verifyIdToken.claims
                it["sub"] = verifyIdToken.uid
                it["username"] = verifyIdToken.uid
            }

            //MappingJwtAuthoritiesConverter 에서 사용함
            if (parse.jwtClaimsSet.claims["roles"] != null) {
                jwtBuilder.claims { claims ->
                    claims["roles"] = parse.jwtClaimsSet.claims["roles"]
                }
            }

        } catch (firebaseAuthException: FirebaseAuthException) {
            throw InvalidBearerTokenException(FIREBASE_VERIFY_ID_TOKEN_EXCEPTION_MESSAGE, firebaseAuthException)
        }
        return Mono.just(jwtBuilder.build())
    }
}