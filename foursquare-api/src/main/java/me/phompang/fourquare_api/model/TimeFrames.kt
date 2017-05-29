package me.phompang.fourquare_api.model

/**
 * Created by phompang on 5/29/2017 AD.
 */
data class TimeFrames(val day: String,
                      val open: List<Segment>,
                      val includesToday: Boolean,
                      val segments: List<Segment>)
