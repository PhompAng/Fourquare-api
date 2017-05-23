package me.phompang.fourquare_api.exception

/**
 * Created by phompang on 5/20/2017 AD.
 */
class FoursquareApiException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(throwable: Throwable) : super(throwable)
}
