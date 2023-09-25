package com.monir.marvelapp.base

import com.google.gson.annotations.SerializedName

open class BaseDataContainer <N> {

    @SerializedName("offset")
    var offset: Int = 0

    @SerializedName("limit")
    var limit: Int = 0

    @SerializedName("total")
    var total: Int = 0

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("results")
    var data: ArrayList<N>? = null

}