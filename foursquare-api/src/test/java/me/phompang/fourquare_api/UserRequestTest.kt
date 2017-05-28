package me.phompang.fourquare_api

import io.reactivex.observers.TestObserver
import me.phompang.fourquare_api.model.Result
import me.phompang.fourquare_api.model.user.CompactUser
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
 * Created by phompang on 5/27/2017 AD.
 */
class UserRequestTest {

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
    fun testRequest() {
        val json: String = IOUtils.toString(javaClass.classLoader.getResourceAsStream("data/user/request/self.json"), Charset.defaultCharset())
        val response: MockResponse = MockResponse().setResponseCode(200).setBody(json)
        server.enqueue(response)

        val observer: TestObserver<Result<List<CompactUser>>> = TestObserver()

        foursquareApi!!.usersRequests().subscribe(observer)
        observer.awaitTerminalEvent()
        observer.assertComplete()
                .assertNoErrors()
                .assertValue {
                    t: Result<List<CompactUser>>? -> t!!.meta.code.toInt() == 200
                }
                .assertValue {
                    t: Result<List<CompactUser>>? -> t!!.response.size == 10
                }
                .assertValue {
                    t: Result<List<CompactUser>>? -> t!!.response[0].id =="122787059"
                }
                .assertValue {
                    t: Result<List<CompactUser>>? -> t!!.response[0].firstName == "Worapong"
                }
                .assertValue {
                    t: Result<List<CompactUser>>? -> t!!.response[0].homeCity == "Bangkok, Thailand"
                }
    }


}
