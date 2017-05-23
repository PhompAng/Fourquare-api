package me.phompang.fourquare_api

import io.reactivex.schedulers.Schedulers
import me.phompang.fourquare_api.api.OAuth
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by phompang on 5/19/2017 AD.
 */

class FoursquareApi(val clientId: String,
                    val clientSecret: String,
                    val redirectUrl: String,
                    val retrofit: Retrofit = Retrofit.Builder()
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(MoshiConverterFactory.create())
                            .baseUrl("https://foursquare.com/").build()) {
    var accessToken: String = ""

    fun authenticateCode(code: String) {
        val oauth: OAuth = retrofit.create(OAuth::class.java)

        oauth.getAccessToken(this.clientId, this.clientSecret, this.redirectUrl, code)
                .subscribeOn(Schedulers.newThread())
                .doOnNext {
                    println(it.access_token)
                    accessToken = it.access_token
                }
                .doOnError {
                    accessToken = ""
                }
                .blockingLast()
    }
}
