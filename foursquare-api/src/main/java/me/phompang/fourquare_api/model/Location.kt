package me.phompang.fourquare_api.model

/**
 * Created by phompang on 5/28/2017 AD.
 */
data class Location(val address: String,
                    val crossStreet: String,
                    val lat: Double,
                    val lng: Double,
                    val postalCode: String,
                    val cc: String,
                    val city: String,
                    val state: String,
                    val country: String,
                    val formattedAddress: List<String>,
                    val isFuzzed: Boolean)
