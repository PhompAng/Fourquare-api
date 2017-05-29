package me.phompang.fourquare_api.model.user

import me.phompang.fourquare_api.model.Contact
import me.phompang.fourquare_api.model.Count
import me.phompang.fourquare_api.model.Group
import me.phompang.fourquare_api.model.Photo
import me.phompang.fourquare_api.model.list.List

/**
 * Created by phompang on 5/23/2017 AD.
 */
data class CompleteUser(val id: String,
                        val firstName: String,
                        val lastName: String,
                        val gender: String,
                        val relationship: String,
                        val canonicalUrl: String,
                        val friends: Group<CompactUser>, //TODO MiniUser
                        val photo: Photo,
                        val birthday: Long,
                        val tips: Count<Any>, //TODO type
                        val homeCity: String,
                        val bio: String,
                        val contact: Contact,
                        val photos: Count<Photo>,
                        val type: String,
                        //TODO mayorships
                        //TODO Checkin
                        //TODO Request
                        val lists: Group<List>,
                        val blockedStatus: String,
                        val createdAt: Long,
                        val pageInfo: PageInfo)
//                        val followers: Group<CompactUser>,
//                        val following: Group<CompactUser>

