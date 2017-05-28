package me.phompang.fourquare_api.api

import io.reactivex.Observable
import me.phompang.fourquare_api.model.Result
import me.phompang.fourquare_api.model.user.CompleteUser
import me.phompang.fourquare_api.model.user.UserResult
import me.phompang.fourquare_api.model.user.UserSearchResult
import me.phompang.fourquare_api.model.user.UsersRequestsResult
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

    @GET("users/search?v=20170523")
    fun searchUser(@Query("phone") phone: String?,
                   @Query("email") email: String?,
                   @Query("twitter") twitter: String?,
                   @Query("twitterSource") twitterSource: String?,
                   @Query("fbid") fbid: String?,
                   @Query("name") name: String?,
                   @Query("onlyPages") onlyPages: Boolean = false,
                   @Query("oauth_token") oauth_token: String): Observable<Result<UserSearchResult>>

    @GET("users/requests?v=20170523")
    fun usersRequests(@Query("oauth_token") oauth_token: String): Observable<Result<UsersRequestsResult>>

}
