package me.phompang.fourquare_api.model

/**
 * Created by phompang on 5/24/2017 AD.
 */
data class Result<out T: Any>(val response: T, val meta: Meta)
