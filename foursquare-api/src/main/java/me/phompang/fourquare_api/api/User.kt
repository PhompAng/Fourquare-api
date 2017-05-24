package me.phompang.fourquare_api.api

import io.reactivex.Observable
import me.phompang.fourquare_api.model.Result
import me.phompang.fourquare_api.model.user.CompleteUser
import me.phompang.fourquare_api.model.user.UserResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by phompang on 5/24/2017 AD.
 */
interface User {
    @GET("users/{id}?v=20170523")
    fun getUser(@Path("id") userId: String,
                @Query("oauth_token") oauth_token: String): Observable<Result<UserResult<CompleteUser>>>
}
