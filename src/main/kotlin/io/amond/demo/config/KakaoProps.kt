package io.amond.demo.config

import io.amond.demo.infra.FirebaseAttributes
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "kakao")
data class KakaoProps(
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String
)
