package me.phompang.fourquare_api.model

/**
 * Created by phompang on 5/25/2017 AD.
 */
data class Count<out T>(val type: String,
                        val name: String,
                        val count: Integer,
                        val items: List<T>)
