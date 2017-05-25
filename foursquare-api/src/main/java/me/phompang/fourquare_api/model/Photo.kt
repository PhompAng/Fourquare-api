package me.phompang.fourquare_api.model

import me.phompang.fourquare_api.model.user.CompactUser

/**
 * Created by phompang on 5/23/2017 AD.
 */
data class Photo(val id: String,
                 val createdAt: Long,
                 val prefix: String,
                 val suffix: String,
                 val width: Integer,
                 val height: Integer,
                 val visibility: String,
                 val source: Source,
                 val user: CompactUser)
//TODO venue
//TODO tip
//TODO checkin
