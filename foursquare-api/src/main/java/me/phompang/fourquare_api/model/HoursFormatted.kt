package me.phompang.fourquare_api.model

/**
 * Created by phompang on 5/28/2017 AD.
 */
data class HoursFormatted(val status: String,
                          val isOpen: Boolean,
                          val isLocalHoliday: Boolean,
                          val timeframes: List<TimeFrames>)
