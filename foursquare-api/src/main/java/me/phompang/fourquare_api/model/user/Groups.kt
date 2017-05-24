package me.phompang.fourquare_api.model.user

/**
 * Created by phompang on 5/23/2017 AD.
 */
data class Groups<out T>(val type: String,
                  val name: String,
                  val count: Integer,
                  val item: List<T>)
