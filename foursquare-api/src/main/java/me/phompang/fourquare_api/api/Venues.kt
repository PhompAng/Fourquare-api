package me.phompang.fourquare_api.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by phompang on 5/28/2017 AD.
 */

interface Venues {
    @GET("users/{id}?v=20170523")
    fun venues(@Path("id") venuesId: String,
               @Query("oauth_token") oauth_token: String)
}
