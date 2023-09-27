package com.monir.marvelapp.base

import com.google.gson.annotations.SerializedName

open class BaseResource<T>(
    @SerializedName("data")
    val data: BaseDataContainer<T>? = null,
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("message")
    val message: String? = null
) {
    class Success<T>(data: BaseDataContainer<T>) : BaseResource<T>(data)
    data class Error<T>(val errorType: ErrorType) : BaseResource<T>()
    class Loading<T> : BaseResource<T>()
}