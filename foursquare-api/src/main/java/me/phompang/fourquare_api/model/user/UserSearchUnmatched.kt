package me.phompang.fourquare_api.model.user

/**
 * Created by phompang on 5/25/2017 AD.
 */
data class UserSearchUnmatched(val phone: List<String>,
                               val email: List<String>,
                               val twitter: List<String>,
                               val twitterSource: List<String>,
                               val fbid: List<String>,
                               val name: List<String>)
