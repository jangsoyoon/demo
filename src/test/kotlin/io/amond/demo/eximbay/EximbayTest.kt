package io.amond.demo.eximbay

import mu.KotlinLogging
import org.conscrypt.OpenSSLCipherRSA.OAEP.SHA256
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.ActiveProfiles
import java.security.MessageDigest

private val log = KotlinLogging.logger {}

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class EximbayTest {

    @Test
    fun test() {
        val reqUrl = "https://secureapi.test.eximbay.com/Gateway/BasicProcessor.krp"

        val reqTemp = HashMap<String, String>()

        reqTemp["ver"] = "1.0"
        reqTemp["mid"] = "2E4E17C75E"
        reqTemp["txntype"] = "PAYMENT"
        reqTemp["ref"] = "20251017EximbayTest"
        reqTemp["cur"] = "KRW"
        reqTemp["amt"] = "5000"
        reqTemp["paymethod"] = "P000"
        reqTemp["buyer"] = "장소윤"
        reqTemp["email"] = "sasha@amond.io"
        reqTemp["lang"] = "KR"
        reqTemp["returnurl"] = "https://api.staging.heavenly.tv/api/v1/payment/eximbay/return"
        reqTemp["statusurl"] = "https://api.staging.heavenly.tv/api/v1/payment/eximbay/status"
        reqTemp["param1"] = "1"
        reqTemp["charset"] = "UTF-8"
        reqTemp["ostype"] = "P"// P:PC M:MOBILE
        reqTemp["displaytype"] = "P"

        reqTemp["item_0_product"] = "HEAVENLY MEMBERSHIP"
        reqTemp["item_0_quantity"] = "1"
        reqTemp["item_0_unitPrice"] = "5000"

        val sorted = reqTemp.toSortedMap().entries.joinToString("&") { "${it.key}=${it.value}" }
        val secretKey = "720C31895E034D696E65E01EB5EEAE5E"
        val inputKey = "$secretKey?$sorted"
        val fgKey = MessageDigest.getInstance("SHA-256").digest(inputKey.toByteArray(charset("UTF-8")))
            .joinToString("") { "%02X".format(it) }


        log.info("sorting : $sorted")
        log.info("bytes : $fgKey")

    }
}