package me.phompang.fourquare_api

import io.reactivex.observers.TestObserver
import me.phompang.fourquare_api.model.Result
import me.phompang.fourquare_api.model.user.CompleteUser
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.apache.commons.io.IOUtils
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.nio.charset.Charset

/**
 * Created by phompang on 5/24/2017 AD.
 */
class UserUnitTest {

    companion object {
        var foursquareApi: FoursquareApi? = null
        var server: MockWebServer = MockWebServer()

        @BeforeClass
        @JvmStatic fun setUp() {
            server.start()
            val retrofit: Retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(server.url("/")).build()
            foursquareApi = FoursquareApi("CLIENT_ID", "CLIENT_SECRET", "REDIRECT_URL", retrofit)
            foursquareApi!!.accessToken = "FAKE_TOKEN"
        }
    }

    @Test
    fun testUserSelf() {
        val json: String = IOUtils.toString(javaClass.classLoader.getResourceAsStream("data/user/self.json"), Charset.defaultCharset())
        val response: MockResponse = MockResponse().setResponseCode(200).setBody(json)
        server.enqueue(response)

        val observer: TestObserver<Result<CompleteUser>> = TestObserver()

        foursquareApi!!.getUser("self").subscribe(observer)
        observer.awaitTerminalEvent()
        observer.assertComplete()
                .assertNoErrors()
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.id == "1709482"
                }
                .assertValue {
                    t: Result<CompleteUser>? ->  t!!.response.firstName == "Pichai"
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.photo.suffix == "/HH0ESYINZLKDMFTD.jpg"
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.contact.twitter == "phompang"
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.type == "user"
                }
    }

    @Test
    fun testUserId() {
        val json: String = IOUtils.toString(javaClass.classLoader.getResourceAsStream("data/user/134748312.json"), Charset.defaultCharset())
        val response: MockResponse = MockResponse().setResponseCode(200).setBody(json)
        server.enqueue(response)

        val observer: TestObserver<Result<CompleteUser>> = TestObserver()

        foursquareApi!!.getUser("self").subscribe(observer)
        observer.awaitTerminalEvent()
        observer.assertComplete()
                .assertNoErrors()
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.id == "134748312"
                }
                .assertValue {
                    t: Result<CompleteUser>? ->  t!!.response.firstName == "balabelt"
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.photo.suffix == "/134748312-FCOTGBKFUF50H1LC.jpg"
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.contact.twitter == "balabeltmimi"
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.type == "user"
                }
    }

    @Test
    fun testUserPage() {
        val json: String = IOUtils.toString(javaClass.classLoader.getResourceAsStream("data/user/203469.json"), Charset.defaultCharset())
        val response: MockResponse = MockResponse().setResponseCode(200).setBody(json)
        server.enqueue(response)

        val observer: TestObserver<Result<CompleteUser>> = TestObserver()

        foursquareApi!!.getUser("self").subscribe(observer)
        observer.awaitTerminalEvent()
        observer.assertComplete()
                .assertNoErrors()
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.id == "203469"
                }
                .assertValue {
                    t: Result<CompleteUser>? ->  t!!.response.firstName == "ZAGAT"
                }
                .assertValue {
                    t: Result<CompleteUser>? ->  t!!.response.lastName == null
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.photo.suffix == "/HJPRUD2NFJVZKZ01.png"
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.contact.twitter == "zagat"
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.type == "page"
                }
    }

    @Test
    fun testUserChain() {
        val json: String = IOUtils.toString(javaClass.classLoader.getResourceAsStream("data/user/1070527.json"), Charset.defaultCharset())
        val response: MockResponse = MockResponse().setResponseCode(200).setBody(json)
        server.enqueue(response)

        val observer: TestObserver<Result<CompleteUser>> = TestObserver()

        foursquareApi!!.getUser("self").subscribe(observer)
        observer.awaitTerminalEvent()
        observer.assertComplete()
                .assertNoErrors()
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.id == "1070527"
                }
                .assertValue {
                    t: Result<CompleteUser>? ->  t!!.response.firstName == "Starbucks"
                }
                .assertValue {
                    t: Result<CompleteUser>? ->  t!!.response.lastName == null
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.photo.suffix == "/PM3ZCXLZIG3WZHVC.png"
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.contact.twitter == "starbucks"
                }
                .assertValue {
                    t: Result<CompleteUser>? -> t!!.response.type == "page"
                }
    }
}
