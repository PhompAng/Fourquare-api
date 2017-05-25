package me.phompang.fourquare_api.model.list

import me.phompang.fourquare_api.model.Count
import me.phompang.fourquare_api.model.Photo
import me.phompang.fourquare_api.model.user.CompactUser

/**
 * Created by phompang on 5/25/2017 AD.
 */
data class List(val id: String,
                val name: String,
                val description: String,
                val user: CompactUser,
                val following: Boolean,
                val followers: Count<Any>, //TODO Type
                val editable: Boolean,
                val collaborative: Boolean,
                val collaborators: Count<CompactUser>, //TODO Check
                val canonicalUrl: String,
                val photo: Photo,
                val venueCount: Integer,
                val visitedCount: Integer,
                val listItems: Count<Any> /*TODO list items*/)
