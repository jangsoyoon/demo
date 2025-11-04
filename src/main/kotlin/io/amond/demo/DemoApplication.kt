package io.amond.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("io.amond.demo.config")
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
