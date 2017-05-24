package me.phompang.fourquare_api.model.user

/**
 * Created by phompang on 5/23/2017 AD.
 */
data class Friends(val count: Integer,
                   val groups: List<Groups<CompactUser>>)
