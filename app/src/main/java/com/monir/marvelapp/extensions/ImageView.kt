package com.monir.marvelapp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.monir.marvelapp.data.model.Thumbnail
import com.monir.marvelapp.utils.GeneralUtils

fun ImageView.load(imgUrl: String) = Glide.with(this).load(imgUrl).apply (GeneralUtils.getDefaultGlideUtils()).into(this)
fun ImageView.load(thumbnail: Thumbnail?) {
    thumbnail?.let {
        val imageUrl = "${it.path}.${it.extension}"
        load(GeneralUtils.validateUrl(imageUrl))
    }
}