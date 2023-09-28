package com.monir.marvelapp.data.model

import com.monir.marvelapp.utils.GeneralUtils
import java.io.Serializable

data class Thumbnail(
    val path: String?,
    val extension: String?
) : Serializable {
    val imagePath: String?
        get() = if (path != null && extension != null) GeneralUtils.validateUrl("$path.$extension") else null
}