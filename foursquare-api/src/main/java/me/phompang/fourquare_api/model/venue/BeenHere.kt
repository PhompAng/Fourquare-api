package me.phompang.fourquare_api.model.venue

/**
 * Created by phompang on 5/28/2017 AD.
 */

data class BeenHere(val count: Integer,
                    val unconfirmedCount: Integer,
                    val marked: Boolean,
                    val lastCheckinExpiredAt: Long)
