package me.phompang.fourquare_api.model.user

import me.phompang.fourquare_api.model.Photo

/**
 * Created by phompang on 5/23/2017 AD.
 */
data class CompactUser(val id: String,
                       val firstName: String,
                       val lastName: String,
                       val photo: Photo,
                       val relationship: String,
                       val friends: Friends,
                       val type: String,
                       // venue
                       val homeCity: String,
                       val gender: String,
                       val contact: Contact,
                       val bio: String)
