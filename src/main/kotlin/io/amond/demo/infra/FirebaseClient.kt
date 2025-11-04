package io.amond.demo.infra

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

interface FirebaseAttributes {
    val account: String
    val appName: String
}
private val log = KotlinLogging.logger {}


@Component
class FirebaseClient(
    private val firebaseProps: FirebaseAttributes
) {

    @PostConstruct
    fun init() {
        val serviceAccount = ClassPathResource("${firebaseProps.account}").inputStream
        val options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build()
        FirebaseApp.initializeApp(options)
        log.info { "]]=========== FirebaseClient [${firebaseProps.appName}] ===========[[" }
    }

    @Bean
    fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

}