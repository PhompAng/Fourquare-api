package me.phompang.fourquare_api.model.venue

import me.phompang.fourquare_api.model.*
import me.phompang.fourquare_api.model.tip.Tip
import me.phompang.fourquare_api.model.user.CompactUser

/**
 * Created by phompang on 5/28/2017 AD.
 */
data class CompleteVenue(val id: String,
                         val name: String,
                         val contact: Contact,
                         val location: Location,
                         val categories: List<Category>,
                         val verified: Boolean,
                         val stats: VenueStats,
                         val url: String,
                         val shortUrl: String,
                         val canonicalUrl: String,
                         val closed: Boolean,
                         val price: Price,
                         val hasMenu: Boolean,
                         val likes: Group<CompactUser>, //TODO Miniuser
                         val like: Boolean,
                         val dislike: Boolean,
                         val ok: Boolean,
                         val rating: String,
                         val ratingColor: String,
                         val ratingSignals: String,
                         val menu: Menu,
                         val allowMenuUrlEdit: Boolean,
                         val beenHere: BeenHere,
                         val specials: Count<Special>,
                         val photos: Group<Photo>,
                         val description: String,
                         val storeId: String,
                         val reasons: Count<Reason>,
                         val hereNow: Group<CompactUser>,
                         val createdAt: Long,
                         val tips: Group<Tip>,
                         val tags: List<String>,
                         val timeZone: String,
                         val listed: Group<me.phompang.fourquare_api.model.list.List>,
                         val hours: HoursFormatted,
                         val popular: HoursFormatted,
                         val attributes: Group<Attribute>,
                         val bestPhoto: Photo)
//TODO phrases
//TODO PageUpdate
//TODO Inbox
//TODO venueChains
//TODO specialsNearby
