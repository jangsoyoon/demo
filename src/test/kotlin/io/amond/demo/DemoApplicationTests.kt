package io.amond.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

@SpringBootTest
class DemoApplicationTests {

    private val mapper = ObjectMapper().registerModule(
        KotlinModule.Builder().build()
    ).also {
        it.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        it.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }
    val DEFAULT_LOCALE: Locale = Locale.JAPAN
    val zoneOffset = 9


    @Test
    fun forPlus() {
        var maxSortPosition = 0.0

        (1..5).map { index ->
            maxSortPosition += 1
        }
        println(maxSortPosition)
    }

    @Test
    fun appendTest() {
        val str = "EP."


        (1..5).map { index ->
            var title = StringBuilder(str).append("%02d".format(index))
            println(title)
        }
    }

    @Test
    fun sliceMap() {
        var ids = "1,2,3,4,5,6,7,8,9,10,11"
        var tagIds: MutableSet<Long> = mutableSetOf()

        if (ids.isNotEmpty()) {
            tagIds = ids.split(",").map { it.toLong() }.toMutableSet()
            if (tagIds.size > 10) {
            }
        }


        println(tagIds)
    }

    @Test
    fun nowTest() {
        val nowInstant = Instant.now()

        val plus = nowInstant.plus(7, ChronoUnit.DAYS)
        println(plus)

//
//        // 현재 UTC 시간에서 날짜만 추출
//        val localDate = nowInstant.atOffset(ZoneOffset.UTC).toLocalDate()
//
//        // 해당 날짜에 15:00:00을 추가한 LocalDateTime 생성
//        val localDateTime = LocalDateTime.of(localDate, LocalTime.of(15, 0))
//
//        // LocalDateTime을 UTC 기준으로 Instant로 변환
//        val instantAt15: Instant = localDateTime.toInstant(ZoneOffset.UTC)
//        println(instantAt15)
        val DEFAULT_LOCALE: Locale = Locale.KOREA
        val DEFAULT_LANGUAGE: String = DEFAULT_LOCALE.language
        println(DEFAULT_LOCALE.country)
        println(DEFAULT_LANGUAGE.toString())


    }

    @Test
    fun timeTest() {
        val now = Instant.now()
        val koreaDateTime = now.atZone(ZoneId.of("Asia/Seoul"))
        val recurringDate = koreaDateTime.plus(
            6, ChronoUnit.DAYS
        ).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        println(recurringDate)

        val dayPlus = Instant.parse("2025-04-02T02:14:43Z") // 'T' 구분자와 'Z' 시간대 정보 추가
        val dayPlusDateTime = dayPlus.atZone(ZoneId.of("Asia/Seoul"))

        // 날짜 형식 지정
        val dayPlusDate = dayPlusDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        println(dayPlusDate)

    }

    @Test
    fun bigDecimalTest() {
        val test = BigDecimal("0")
//        println(BigDecimal(test))
//        println(BigDecimal.ZERO)
//        println(BigDecimal.ZERO.compareTo(BigDecimal(test)))
//        println(BigDecimal.ZERO  != BigDecimal(test))
//        println(BigDecimal.ZERO.compareTo(BigDecimal("10")))
//        println(BigDecimal.ZERO.compareTo(BigDecimal("-10")))

        if (test > BigDecimal.ZERO && test != null) {
            println(test)
        }
    }

    @Test
    fun dateTest() {
        val date = LocalDate.now()
        println(date)
        val datePlus = LocalDate.now().plusDays(3)
        println(datePlus)

    }

    @Test
    fun minuteTest() {
        val now = Instant.now()
        println(now)
        val nowPlusMinute = dateFormat(now - Duration.ofMinutes(10))
        println(nowPlusMinute)
        val openAt = "2025-01-15 08:05"


    }

    @Test
    fun dayTest() {
        val zoneOffset = 9
//        val recurringNext = "20240211"
//        val inputNow = "$recurringNext 23:59:59"
//
//        val preEndAt = LocalDateTime.parse(inputNow, DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"))
//            .atOffset(ZoneOffset.ofHours(zoneOffset)).toInstant()
//
//        println(preEndAt)

        val dateFormatPattern =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz").withZone(ZoneOffset.ofHours(zoneOffset))
//        val nowString = Instant.parse("2025-01-22T15:04:46.903274Z")
//        val koreaDateTime = nowString.atZone(ZoneId.of("Asia/Seoul"))
//        val nowStringParse = koreaDateTime.plus(1, ChronoUnit.MONTHS).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
//        println("------------------nowStringParse: ${nowStringParse}")
        val now = Instant.now()
//        println("------------------now: ${now}")

        val duration: Long = 20
        val recurringNext = "20250120"
        val inputNow = "$recurringNext 23:59:59"
        val checkRecurringNext = Instant.parse(dateFormatPattern.format(now.plus(duration, ChronoUnit.DAYS)))
        println("------------------checkRecurringNext: ${checkRecurringNext}")

        val preEndAt = LocalDateTime.parse(inputNow, DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"))
            .atOffset(ZoneOffset.ofHours(zoneOffset))
        println("------------------preEndAt: ${preEndAt}")

        val endDate = if (preEndAt.toInstant() <= checkRecurringNext) {
            println("1")
            preEndAt.plus(1, ChronoUnit.MONTHS)
        } else {
            println("2")
            preEndAt
        }
//        val formattedDateTime = LocalDateTime.parse(dateTime.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")).atOffset(ZoneOffset.ofHours(zoneOffset)).toInstant()
//
//
//        println(formattedDateTime)
        println(endDate)
//
//
//
//

//
//        println(endDate)
//        recurringNext=20250110

//        val dateFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz").withZone(ZoneOffset.ofHours(zoneOffset))
//        val checkRecurringNext = Instant.parse(dateFormatPattern.format(now.plus(duration, ChronoUnit.DAYS)))
//        val preEndAt = LocalDateTime.parse(inputNow, DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"))
////            .atOffset(ZoneOffset.ofHours(zoneOffset))
//        val now = Instant.now()
//        println("UTC time: $now")
//
//        val kst = ZonedDateTime.ofInstant(now, ZoneId.of("Asia/Seoul"))
//        println("KST time: $kst")
//
//        val kstPlusMonth = kst.plusMonths(1)
//        println("KST + 1 month: $kstPlusMonth")
//        val duration = Duration.between(now, kstPlusMonth)
////
//        val daysBetween = duration.toDays()
////
//        println(daysBetween)
    }

    @Test
    fun pairMap() {
//        val list = listOf(
//            test(1, "A", Membership(1, "BASIC")),
//            test(1, "B", Membership(1, "BASIC")),
//            test(1, "A", Membership(2, "BASIC_FAMILY")),
//            test(2, "A", Membership(2, "BASIC_FAMILY")),
//            test(2, "B", Membership(1, "BASIC")),
//        )
//        val result = list.distinctBy { Pair(it.id, it.membership) }.groupBy { it.id }
//        println(result)
    }

    @Test
    fun convertUnixTime() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
        val localDateTime = LocalDateTime.parse("2025-06-09 08:11:11.625376", formatter)
        val epochMilli = localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()
        println(epochMilli)

//        val list = listOf(
//            test(1, "A", Membership(1, "BASIC")),
//            test(1, "B", Membership(1, "BASIC")),
//            test(1, "A", Membership(2, "BASIC_FAMILY")),
//            test(2, "A", Membership(2, "BASIC_FAMILY")),
//            test(2, "B", Membership(1, "BASIC")),
//        )
//        val result = list.distinctBy { Pair(it.id, it.membership) }.groupBy { it.id }
//        println(result)
    }

    @Test
    fun unixTime() {
        val now = LocalDate.now()
        // 오늘 속한 달의 첫 번째 날
        val firstDayOfThisMonth = now.withDayOfMonth(1)

        // 지난달의 지난달 마지막 날
        val lastDayOfLastLastMonth = firstDayOfThisMonth.minusMonths(1).withDayOfMonth(1).minusDays(1)


        val startAt = lastDayOfLastLastMonth.atTime(15, 0)
        val endAt = firstDayOfThisMonth.atTime(15, 0)

        println(startAt)
        println(endAt)

        val startAtUnixTimeMillis = startAt.toInstant(ZoneOffset.UTC).toEpochMilli()
        val endAtUnixTimeMillis = endAt.toInstant(ZoneOffset.UTC).toEpochMilli()
    }

    @Test
    fun listTest() {
        val membershipContents = listOf(
            MembershipContentReq(
                1,
                Instant.parse("2024-10-01T05:47:21.230+00:00"),
                Instant.parse("2025-10-01T05:47:21.230+00:00")
            ),
            MembershipContentReq(
                1,
                Instant.parse("2024-12-01T06:47:21.230+00:00"),
                Instant.parse("2025-10-01T05:47:21.230+00:00")
            ),
            MembershipContentReq(
                2,
                Instant.parse("2025-01-01T06:47:21.230+00:00"),
                Instant.parse("2025-10-01T05:47:21.230+00:00")
            ),
            MembershipContentReq(
                1,
                Instant.parse("2024-10-01T06:47:21.230+00:00"),
                Instant.parse("2025-10-01T05:47:21.230+00:00")
            ),
        ).associate { it.membershipId to (it.startAt to it.endAt) }
        println(membershipContents)
        val memberships = listOf(
            Membership(1, "BASIC", BigDecimal(12900)),
            Membership(2, "BASIC_FAMILY", BigDecimal(12900))
        ).associateBy { it.id }
        val membershipContentList: MutableList<MembershipInfo> = mutableListOf()
        membershipContents.map { membershipContent ->
            if (memberships.containsKey(membershipContent.key)) {
                val membershipContentInfo = MembershipInfo(
                    membership = memberships[membershipContent.key]!!,
                    startAt = membershipContents[membershipContent.key]!!.first,
                    endAt = membershipContents[membershipContent.key]!!.second,
                )

                membershipContentList.add(membershipContentInfo)
            }

        }
        println(membershipContentList)
    }

    @Test
    fun distinctByMembershipId() {
        val membershipContents = listOf(
            MembershipContentReq(
                1,
                Instant.parse("2025-05-27T05:47:21.230+00:00"),
                Instant.parse("2025-06-27T15:00:00.000+00:00")
            ),
            MembershipContentReq(
                1,
                Instant.parse("2025-06-27T06:47:21.230+00:00"),
                Instant.parse("2025-07-27T15:00:00.000+00:00")
            ),
            MembershipContentReq(
                2,
                Instant.parse("2025-01-01T06:47:21.230+00:00"),
                Instant.parse("2025-10-01T05:47:21.230+00:00")
            ),
            MembershipContentReq(
                1,
                Instant.parse("2024-10-01T06:47:21.230+00:00"),
                Instant.parse("2025-10-01T05:47:21.230+00:00")
            ),
        ).sortedByDescending { it.startAt }.distinctBy { it.membershipId }

        println(membershipContents)
    }

    @Test
    fun stringEquals() {
        val DEFAULT_CONTENT_ITEM_VIDEO_DATA_URL: String = "오픈 예정"

        val contentItemVideo = ContentItemVideo(
            dataUrl = "오픈 예정 "
        )

        if (contentItemVideo.dataUrl.equals(DEFAULT_CONTENT_ITEM_VIDEO_DATA_URL)) {
            println("equals")
        }
    }

    @Test
    fun multiplyBigDecimalAppend() {
        val DEFAULT_CONTENT_ITEM_ALL_TITLE: String = "전편"
        val entireProductGuaranteeDuration = 30
        val entireProductDiscountRate = "1.00".toBigDecimal()
        val result = StringBuilder(DEFAULT_CONTENT_ITEM_ALL_TITLE).append(
            " (${entireProductGuaranteeDuration}일, ${
                entireProductDiscountRate.multiply(BigDecimal("1")).toLong()
            }% OFF)"
        ).toString()

        println(result)
    }

    @Test
    fun multiplyBigDecimal() {
        val originPrice = "60".toBigDecimal()
        val discountRate = "0.20".toBigDecimal()
//        val discount = originPrice.multiply(discountRate).setScale(0, RoundingMode.DOWN)
//        val result = originPrice.subtract(discount)

        val discountPrice = if (discountRate != BigDecimal.ZERO) {
            originPrice.multiply(discountRate).setScale(0, RoundingMode.DOWN)
        } else {
            BigDecimal.ZERO
        }

        val price = originPrice.subtract(discountPrice)

        println(discountPrice)
        println(discountRate)
        println(price)
    }

    //    @Test
//    fun splitMap() {
//        val membershipInfos: MutableList<MembershipInfo> = mutableListOf()
//        val test = "1&2024-07-01T06:47:21.230+00:00,2&2024-10-01T06:47:21.230+00:00"
//        test.trim().split(",").map {
//            it.trim().split("&").let { info ->
//                val membershipContent = MembershipInfo(
//                    id = info[0].toLong(),
//                    startAt = Instant.parse(info[1])
//                )
//                membershipInfos.add(membershipContent)
//            }
//        }
//
//        println(membershipInfos)
//    }
    @Test
    fun dateTimeTest() {
        val local = LocalDate.now()
        println(local)
    }

    @Test
    fun checkNicknameValid() {
        val nicknamePattern = "^[^'\"-;*?/&=#%+.\\s]+$"
        val nickName = "앙녕"
        val nicknameValid = Regex(nicknamePattern)
        println(nicknameValid.matches(nickName))
    }

    @Test
    fun checkUrlValid() {
//        val urlPattern = "^(https?://)?[\\w.-]+\\.[a-z]{2,6}(/[^\\s]*)?$"
        val youtubeUrlPattern = "^(https?://)?(www\\.)?(youtube\\.com/watch\\?v=|youtu\\.be/)[A-Za-z0-9_-]{11}(&\\S*)?$"
//        val url = "https://www.youtube.com/watch?v=nCG7ZkBfr6I"
        val url = "https://www.naver.com"
        val urlValid = Regex(youtubeUrlPattern)
        println(urlValid.matches(url))
    }

    @Test
    fun filterCheckTest() {

        val contentItemId: Long = 6
        val contentItems = listOf(
            ContentItem(id = 1, isFinalEpisode = false),
            ContentItem(id = 2, isFinalEpisode = false),
            ContentItem(id = 3, isFinalEpisode = false),
            ContentItem(id = 4, isFinalEpisode = false),
            ContentItem(id = 5, isFinalEpisode = false),
            ContentItem(id = 6, isFinalEpisode = true),
        )

        val findContentItem = contentItems.filter { it.isFinalEpisode && it.id != contentItemId }
        println(findContentItem)

    }

    @Test
    fun priceTest() {
        val singleProductPrice = BigDecimal("4")
//        val episodeCountTitle = 8;
        val originPrice = BigDecimal("30")
        val discountRate = BigDecimal("0.15")
        val discountPrice = if (discountRate != BigDecimal.ZERO) {
            originPrice.multiply(discountRate).setScale(0, RoundingMode.DOWN)
        } else {
            BigDecimal.ZERO
        }

        val price = originPrice.subtract(discountPrice)

        println(price)
        println(discountPrice)
    }

    @Test
    fun inputKeySortTest() {
        val keys = listOf(
            "AUTO_SINGLE_INITIAL_123_2025-06-25T01:00:00Z_3242",
            "AUTO_SINGLE_INITIAL_123_2025-06-25T03:00:00Z_22224",
            "AUTO_SINGLE_INITIAL_123_2025-06-25T02:00:00Z_33354363"
        )

        val sorted = keys.sorted()
        println(sorted)
    }

    @Test
    fun payStartAt() {
        val now = Instant.now()


        val list = listOf(
            ProductContentItem(Instant.parse("2025-06-13T00:00:00.000+00:00")),
            ProductContentItem(Instant.parse("2025-07-13T00:00:00.000+00:00")),
            ProductContentItem(Instant.parse("2025-08-13T00:00:00.000+00:00")),
            ProductContentItem(Instant.parse("2025-09-13T00:00:00.000+00:00")),
            ProductContentItem(Instant.parse("2025-10-13T00:00:00.000+00:00")),
            ProductContentItem(Instant.parse("2025-05-13T00:00:00.000+00:00")),
            ProductContentItem(Instant.parse("2025-12-13T00:00:00.000+00:00"))
        )

        val filterList = list.filter { it.payStartAt?.isBefore(now) == true }
        if (filterList.isNotEmpty()) {
            println("에러")
        } else {
            println(filterList)
        }
    }

    @Test
    fun findMin() {
        val list = listOf(
            Membership(1, "A", BigDecimal(3000)),
            Membership(2, "A", BigDecimal(3500)),
            Membership(3, "C", BigDecimal(4000)),
            Membership(4, "D", BigDecimal(4500)),
            Membership(5, "E", BigDecimal(5000)),
            Membership(6, "F", BigDecimal(5500))
        )
        val minBy = list.minBy { it.priceKrw }
        println(minBy)
    }

    @Test
    fun filterList() {
        val list = listOf(
            Membership(1, "A", BigDecimal(7000)),
            Membership(2, "B", BigDecimal(3000)),
        )

        var list1 = listOf(
            Membership(1, "A", BigDecimal(3000)),
            Membership(3, "B", BigDecimal(3000)),
        )

        list1 = list1.filterNot { list1 -> list.map { it.id }.contains(list1.id) }
        println(list1)
    }

    @Test
    fun minList() {
        val list = listOf(
            MembershipContentReq(
                1,
                Instant.parse("2024-10-01T05:47:21.230+00:00"),
                Instant.parse("2025-10-01T05:47:21.230+00:00")
            ),
            MembershipContentReq(
                1,
                Instant.parse("2024-12-01T06:47:21.230+00:00"),
                Instant.parse("2025-10-01T05:47:21.230+00:00")
            ),
            MembershipContentReq(
                2,
                Instant.parse("2025-01-01T06:47:21.230+00:00"),
                Instant.parse("2025-10-01T05:47:21.230+00:00")
            ),
            MembershipContentReq(
                1,
                Instant.parse("2024-10-01T06:47:21.230+00:00"),
                Instant.parse("2025-10-01T05:47:21.230+00:00")
            ),
        )

        val orderContentIds = list
            .groupBy { it.membershipId }
            .mapValues { entry ->
                entry.value.minByOrNull { it.startAt }
            }
            .map { it.key to it.value!!.startAt }
            .toMap()

        println(orderContentIds)
    }

    @Test
    fun locale() {
        val DEFAULT_LOCALE: Locale = Locale.KOREA
        val DEFAULT_LANGUAGE: String = DEFAULT_LOCALE.language

        println(DEFAULT_LANGUAGE)
    }

    @Test
    fun calculate() {
        val membershipPrice = BigDecimal("12900.00")
        val VAT: BigDecimal = BigDecimal("1.1")
        val CARD_FEE: BigDecimal = BigDecimal("0.97")
        val platFormMembershipPrice = membershipPrice.div(VAT).multiply(CARD_FEE).setScale(1, RoundingMode.DOWN)
        println(platFormMembershipPrice)
        val membershipUsingTotalDate = 28

        val price = platFormMembershipPrice.divide(membershipUsingTotalDate.toBigDecimal(), 10, RoundingMode.DOWN)
            .setScale(1, RoundingMode.DOWN)
//        val price = fruitPrice.div(date.toBigDecimal())
//
//// 첫째 자리까지 자르기
//        val roundedPrice = price.setScale(1, RoundingMode.DOWN)

        println(price)
    }

    @Test
    fun test() {
        val response =
            "recurringID=D354564E25A42ED051A7&mid=D354564E25&txntype=RC_TERMINATE&resmsg=Success.&param3=&rescode=0000&param1=&param2="
        val locale = DEFAULT_LOCALE
        println(locale.country)

        val map = response.split("&").associate {
            val value = it.split("=")
            val key = if (value[0] == "recurringID") {
                value[0].replace("recurringID", "recurring_id")
            } else {
                value[0]
            }
            key to value[1]
        }


        val result = mapper.convertValue(map, EximbayRecurringCancelRes::class.java).apply {
            resultInfos = response
        }

        println(map)

        println(result)
    }

    @Test
    fun test2() {
        var sum = 0L
        val response = listOf(
            Sum(1, 3000),
            Sum(1),
            Sum(1, 2000),
            Sum(1, 6000),
            Sum(1, 4000),
        )

        sum = response.sumOf { it.price ?: 0 }
        println(sum)
    }

    fun dateFormat(timestamp: Instant): String {
        val dateFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz").withZone(ZoneOffset.UTC)
        return dateFormatPattern.format(timestamp)
    }

    data class test(
        var id: Long,
        var membership: Membership
    )

    data class Membership(
        var id: Long,
        var name: String,
        var priceKrw: BigDecimal,
        var priceUsd: BigDecimal? = null,
        var priceJpy: BigDecimal? = null,
    )

    data class MembershipInfo(
        var membership: Membership,
        var startAt: Instant,
        var endAt: Instant,
    )

    data class Sum(
        var id: Long,
        var price: Long? = null,
    )

    data class MembershipContentReq(
        var membershipId: Long,
        var startAt: Instant,
        var endAt: Instant,
    )

    data class MembershipInfoMembership(
        var membership: Membership,
        var startAt: Instant,
    )

    data class EximbayRecurringCancelRes(
        var mid: String,
        var txntype: String? = null,
        var recurringID: String,
        var rescode: String? = null,
        var resmsg: String? = null,
        var param1: String? = null,
        var param2: String? = null,
        var param3: String? = null,
        var resultInfos: String? = null,
    )

    data class ContentItemVideo(
        var dataUrl: String,
    )

    data class ProductContentItem(
        var payStartAt: Instant? = null,
    )

    data class ContentItem(
        var id: Long,
        var isFinalEpisode: Boolean,
    )

    data class Content(
        var id: Long,
        var event: Event,
    )

    data class Event(
        var id: Long,
    )

    data class Product(
        var id: Long,
    )
}
