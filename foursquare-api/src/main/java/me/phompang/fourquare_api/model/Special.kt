package me.phompang.fourquare_api.model

import me.phompang.fourquare_api.model.user.CompactUser

/**
 * Created by phompang on 5/28/2017 AD.
 */
data class Special(val id: String,
                   val type: String,
                   val message: String,
                   val description: String,
                   val finePrint: String,
                   val unlocked: Boolean,
                   val icon: Photo,
                   val title: String,
                   val state: String,
                   val progress: Integer,
                   val progressDescription: String,
                   val Detail: Integer,
                   val target: Integer,
                   val friendsHere: List<CompactUser>)
