package me.phompang.fourquare_api

import com.squareup.moshi.Types
import io.reactivex.Observable
import io.reactivex.rxkotlin.blockingSubscribeBy
import io.reactivex.schedulers.Schedulers
import me.phompang.fourquare_api.api.OAuth
import me.phompang.fourquare_api.api.User
import me.phompang.fourquare_api.model.Result
import me.phompang.fourquare_api.model.user.CompleteUser
import me.phompang.fourquare_api.model.user.UserResult
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

    fun getUser(userId: String): Observable<Result<CompleteUser>> {
        val user: User = retrofit.create(User::class.java)

        return user.getUser(userId, accessToken)
                .map {
                    t: Result<UserResult<CompleteUser>>? -> Result(t!!.response.user, t.meta)
                }
                .onErrorReturn {
                    t: Throwable? ->
                    val converter: Converter<ResponseBody, Result<CompleteUser>> = retrofit.responseBodyConverter(Types.newParameterizedType(Result::class.java, CompleteUser::class.java), Result::class.java.annotations)
                    converter.convert((t as HttpException).response().errorBody())
                }
    }
}
