package com.monir.marvelapp.utils

import com.bumptech.glide.request.RequestOptions
import java.math.BigInteger
import java.security.MessageDigest

object GeneralUtils {

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun getDefaultGlideUtils() : RequestOptions = RequestOptions()
        /*.placeholder(R.drawable.loading)
        .error(R.drawable.nopic)*/

    fun validateUrl(url: String): String {
        return url.replace("http://", "https://")
    }

}