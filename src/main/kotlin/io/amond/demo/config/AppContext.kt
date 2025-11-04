package io.amond.demo.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.web.reactive.function.client.WebClient
import java.time.Instant
import java.time.format.DateTimeFormatterBuilder
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

@Configuration
class AppContext {
    val readTimeoutSecond = 30
    val writeTimeoutSecond = 30

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }

//    @Bean
//    fun webClient(): WebClient {
//        val sslContextForTls12: SslContext = SslContextBuilder.forClient()
//            .protocols("TLSv1.2")
//            .build()
//        val httpClientForTls12: HttpClient = HttpClient.create()
//            .secure { ssl -> ssl.sslContext(sslContextForTls12) }
//            .doOnConnected { conn ->
//                conn.addHandlerLast(ReadTimeoutHandler(readTimeoutSecond))
//                    .addHandlerLast(WriteTimeoutHandler(writeTimeoutSecond))
//            }
//        return WebClient.builder()
//            .clientConnector(ReactorClientHttpConnector(httpClientForTls12))
//            .build()
//    }

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .build()
    }

    @Bean
    fun taskScheduler(): TaskScheduler {
        val threadPoolTaskScheduler = ThreadPoolTaskScheduler()
        threadPoolTaskScheduler.poolSize = 10
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler")
        return threadPoolTaskScheduler
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper().registerKotlinModule()
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).apply {
            val javaTimeModule = JavaTimeModule()
            javaTimeModule.addSerializer(Instant::class.java, Iso8601WithoutMillisInstantSerializer())
            registerModule(javaTimeModule)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return mapper
    }

}

private class Iso8601WithoutMillisInstantSerializer :
    InstantSerializer(INSTANCE, false, DateTimeFormatterBuilder().appendInstant(0).toFormatter())

