package com.monir.marvelapp.utils

import com.monir.marvelapp.BuildConfig

object AppConstants {

    const val BASE_URL    = "https://gateway.marvel.com:443/v1/public/"
    const val API_KEY     = "cfdd01ea5c6dab750cf390cc1f21f28e"
    // This usually should be store in a more secure place like the keystore
    const val PRIVATE_KEY = "bbe38c9f366049d5ee404aa71fcdcd4b2073a33a"

    fun isDebugVersion() = BuildConfig.DEBUG
}