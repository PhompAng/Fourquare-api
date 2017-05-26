package me.phompang.fourquare_api.model.user

/**
 * Created by phompang on 5/25/2017 AD.
 */
data class UserSearchResult(val results: List<CompactUser>,
                            val unmatched: UserSearchUnmatched)
