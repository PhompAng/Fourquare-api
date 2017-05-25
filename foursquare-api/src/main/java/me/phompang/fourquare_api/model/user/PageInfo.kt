package me.phompang.fourquare_api.model.user

import me.phompang.fourquare_api.model.Count
import me.phompang.fourquare_api.model.Link

/**
 * Created by phompang on 5/25/2017 AD.
 */
data class PageInfo(val description: String,
                    val banner: String,
                    val links: Count<Link>)
