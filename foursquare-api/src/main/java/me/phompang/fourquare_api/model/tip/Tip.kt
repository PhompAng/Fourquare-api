package me.phompang.fourquare_api.model.tip

import me.phompang.fourquare_api.model.Group
import me.phompang.fourquare_api.model.Photo
import me.phompang.fourquare_api.model.user.CompactUser
import me.phompang.fourquare_api.model.venue.CompleteVenue

/**
 * Created by phompang on 5/28/2017 AD.
 */
data class Tip(val id: String,
               val text: String,
               val createdAt: Long,
               val type: String,
               val canonicalUrl: String,
               val photo: Photo,
               val likes: Group<CompactUser>,
               val like: Boolean,
               val logView: Boolean,
               val agreeCount: Integer,
               val disagreeCount: Integer,
               val todo: Group<CompactUser>,
               val status: Group<CompactUser>,
               val venue: CompleteVenue, //TODO CompactVenue
               val user: CompactUser /*TODO Miniuser*/)
