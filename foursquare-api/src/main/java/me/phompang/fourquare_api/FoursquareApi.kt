package me.phompang.fourquare_api

import com.squareup.moshi.Types
import io.reactivex.Observable
import io.reactivex.rxkotlin.blockingSubscribeBy
import io.reactivex.schedulers.Schedulers
import me.phompang.fourquare_api.api.OAuth
import me.phompang.fourquare_api.api.User
import me.phompang.fourquare_api.model.Result
import me.phompang.fourquare_api.model.user.*
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
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
                            .baseUrl("https://api.foursquare.com/v2/").build()) {
    var accessToken: String = ""

    fun authenticateCode(code: String, url: String = "https://foursquare.com/oauth2/access_token?grant_type=authorization_code") {
        val oauth: OAuth = retrofit.create(OAuth::class.java)

        oauth.getAccessToken(url, this.clientId, this.clientSecret, this.redirectUrl, code)
                .subscribeOn(Schedulers.newThread())
                .blockingSubscribeBy(
                        onNext = {
                            println(it.access_token)
                            accessToken = it.access_token
                        },
                        onError = {
                            accessToken = ""
                        })
    }

    fun users(userId: String): Observable<Result<CompleteUser>> {
        val userApi: User = retrofit.create(User::class.java)

        return userApi.users(userId, accessToken)
                .map {
                    t: Result<UsersResult<CompleteUser>>? -> Result(t!!.response.user, t.meta)
                }
                .onErrorReturn {
                    t: Throwable? ->
                    val converter: Converter<ResponseBody, Result<CompleteUser>> = retrofit.responseBodyConverter(Types.newParameterizedType(Result::class.java, CompleteUser::class.java), Result::class.java.annotations)
                    converter.convert((t as HttpException).response().errorBody())
                }
    }

    fun usersSearch(phone: String? = null,
                    email: String? = null,
                    twitter: String? = null,
                    twitterSource: String? = null,
                    fbid: String? = null,
                    name: String? = null,
                    onlyPages: Boolean = false): Observable<Result<UsersSearchResult>> {
        val userApi: User = retrofit.create(User::class.java)

        return userApi.usersSearch(phone, email, twitter, twitterSource, fbid, name, onlyPages, accessToken)
                .onErrorReturn {
                    t: Throwable? ->
                    val converter: Converter<ResponseBody, Result<UsersSearchResult>> = retrofit.responseBodyConverter(Types.newParameterizedType(Result::class.java, UsersSearchResult::class.java), Result::class.java.annotations)
                    converter.convert((t as HttpException).response().errorBody())
                }
    }

    fun usersRequests(): Observable<Result<List<CompactUser>>> {
        val userApi: User = retrofit.create(User::class.java)

        return userApi.usersRequests(accessToken)
                .map {
                    t: Result<UsersRequestsResult>? -> Result(t!!.response.requests, t.meta)
                }
                .onErrorReturn {
                    t: Throwable? ->
                    val converter: Converter<ResponseBody, Result<List<CompactUser>>> = retrofit.responseBodyConverter(Types.newParameterizedType(Result::class.java, List::class.java, CompactUser::class.java), Result::class.java.annotations)
                    converter.convert((t as HttpException).response().errorBody())
                }
    }
}
