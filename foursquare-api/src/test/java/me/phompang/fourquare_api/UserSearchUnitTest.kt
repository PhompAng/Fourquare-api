package me.phompang.fourquare_api

import io.reactivex.observers.TestObserver
import me.phompang.fourquare_api.model.Result
import me.phompang.fourquare_api.model.user.UsersSearchResult
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
 * Created by phompang on 5/25/2017 AD.
 */
class UserSearchUnitTest {

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
    fun testSearchByPhone() {
        val json: String = IOUtils.toString(javaClass.classLoader.getResourceAsStream("data/user/search/phone.json"), Charset.defaultCharset())
        val response: MockResponse = MockResponse().setResponseCode(200).setBody(json)
        server.enqueue(response)

        val observer: TestObserver<Result<UsersSearchResult>> = TestObserver()

        foursquareApi!!.usersSearch(phone = "1234").subscribe(observer)
        observer.awaitTerminalEvent()
        observer.assertComplete()
                .assertNoErrors()
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.meta.code.toInt() == 200
                }
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.response.results.size == 2
                }
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.response.results[0].id == "22298413"
                }
                .assertValue { 
                    t: Result<UsersSearchResult>? -> t!!.response.results[0].firstName == "Manatsanan"
                }
                .assertValue { 
                    t: Result<UsersSearchResult>? -> t!!.response.results[0].homeCity == "Bangkok Metropolis"
                }
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.response.unmatched.phone.size == 0
                }
    }

    @Test
    fun testSearchMultipleParam() {
        val json: String = IOUtils.toString(javaClass.classLoader.getResourceAsStream("data/user/search/multiple.json"), Charset.defaultCharset())
        val response: MockResponse = MockResponse().setResponseCode(200).setBody(json)
        server.enqueue(response)

        val observer: TestObserver<Result<UsersSearchResult>> = TestObserver()

        foursquareApi!!.usersSearch(name="lorem", fbid="999999,1234", onlyPages=true).subscribe(observer)
        observer.awaitTerminalEvent()
        observer.assertComplete()
                .assertNoErrors()
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.meta.code.toInt() == 200
                }
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.response.results.size == 8
                }
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.response.results[0].id == "42625708"
                }
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.response.results[0].firstName == "Generito"
                }
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.response.unmatched.fbid.size == 2
                }
    }

    @Test
    fun testUserParamError() {
        val json: String = IOUtils.toString(javaClass.classLoader.getResourceAsStream("data/user/search/param_error.json"), Charset.defaultCharset())
        val response: MockResponse = MockResponse().setResponseCode(400).setBody(json)
        server.enqueue(response)

        val observer: TestObserver<Result<UsersSearchResult>> = TestObserver()

        foursquareApi!!.usersSearch().subscribe(observer)
        observer.awaitTerminalEvent()
        observer
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.meta.code.toInt() == 400
                }
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.meta.errorType == "param_error"
                }
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.meta.errorDetail == "Must specify one of phone, fbid, fbSource, twitter, twitterSource, userIds, email, name, bbmId, any."
                }
                .assertValue {
                    t: Result<UsersSearchResult>? -> t!!.response.results == null
                }
    }
}
