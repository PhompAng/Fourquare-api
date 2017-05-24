package me.phompang.fourquare_api.api

import io.reactivex.Observable
import me.phompang.fourquare_api.model.oauth.OAuthResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by phompang on 5/19/2017 AD.
 */
interface OAuth {
    @GET
    fun getAccessToken(@Url url: String,
                       @Query("client_id") clientId: String,
                       @Query("client_secret") clientSecret: String,
                       @Query("redirect_uri") redirectUrl: String,
                       @Query("code") code: String): Observable<OAuthResponse>
}
