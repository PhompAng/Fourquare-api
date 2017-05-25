package me.phompang.fourquare_api.model

/**
 * Created by phompang on 5/25/2017 AD.
 */
data class Group<out T>(val type: String,
                        val name: String,
                        val count: Integer,
                        val groups: List<Count<T>>)

