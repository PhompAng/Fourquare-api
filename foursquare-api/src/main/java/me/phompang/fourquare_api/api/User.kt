package me.phompang.fourquare_api.api

import io.reactivex.Observable
import me.phompang.fourquare_api.model.Result
import me.phompang.fourquare_api.model.user.CompleteUser
import me.phompang.fourquare_api.model.user.UsersResult
import me.phompang.fourquare_api.model.user.UsersSearchResult
import me.phompang.fourquare_api.model.user.UsersRequestsResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by phompang on 5/24/2017 AD.
 */
interface User {
    @GET("users/{id}?v=20170523")
    fun users(@Path("id") userId: String,
              @Query("oauth_token") oauth_token: String): Observable<Result<UsersResult<CompleteUser>>>

    @GET("users/search?v=20170523")
    fun usersSearch(@Query("phone") phone: String?,
                    @Query("email") email: String?,
                    @Query("twitter") twitter: String?,
                    @Query("twitterSource") twitterSource: String?,
                    @Query("fbid") fbid: String?,
                    @Query("name") name: String?,
                    @Query("onlyPages") onlyPages: Boolean = false,
                    @Query("oauth_token") oauth_token: String): Observable<Result<UsersSearchResult>>

    @GET("users/requests?v=20170523")
    fun usersRequests(@Query("oauth_token") oauth_token: String): Observable<Result<UsersRequestsResult>>

}
