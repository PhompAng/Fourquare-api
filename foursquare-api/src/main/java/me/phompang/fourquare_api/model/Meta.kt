package me.phompang.fourquare_api.model

/**
 * Created by phompang on 5/25/2017 AD.
 */
data class Meta(val code: Integer,
                val errorType: String,
                val errorDetail: String,
                val errorMessage: String,
                val requestId: String)
