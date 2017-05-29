package me.phompang.fourquare_api.model.venue

import me.phompang.fourquare_api.model.Photo

/**
 * Created by phompang on 5/28/2017 AD.
 */
data class Category(val id: String,
                    val name: String,
                    val pluralName: String,
                    val shortName: String,
                    val icon: Photo,
                    val primary: Boolean,
                    val categories: List<Category>)
