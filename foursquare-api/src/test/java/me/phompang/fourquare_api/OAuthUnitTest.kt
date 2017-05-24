package me.phompang.fourquare_api

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.apache.commons.io.IOUtils
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.nio.charset.Charset

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class OAuthUnitTest {

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
        }
    }

    @Test
    fun testPassAuthenticateCode() {
        val json: String = IOUtils.toString(javaClass.classLoader.getResourceAsStream("data/auth/oauth.json"), Charset.defaultCharset())
        val response: MockResponse = MockResponse().setResponseCode(200).setBody(json)
        server.enqueue(response)

        foursquareApi!!.authenticateCode("code", server.url("/").toString())
        assertEquals("FAKE_TOKEN", foursquareApi!!.accessToken)
    }

    @Test()
    fun testFailAuthenticateCode() {
        val response: MockResponse = MockResponse().setResponseCode(500)
        server.enqueue(response)
        foursquareApi!!.authenticateCode("code")
        assertEquals("", foursquareApi!!.accessToken)
    }
}
