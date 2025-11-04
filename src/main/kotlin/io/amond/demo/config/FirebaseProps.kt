package io.amond.demo.config

import io.amond.demo.infra.FirebaseAttributes
import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "firebase")
data class FirebaseProps(
    override val account: String,
    override val appName: String
) : FirebaseAttributes

