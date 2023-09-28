package com.monir.marvelapp.extensions

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.monir.marvelapp.R

fun ImageView.load(imgUrl: String?, @DrawableRes placeholderRes : Int = R.drawable.ic_placeholder, @DrawableRes errorRes : Int = R.drawable.ic_error) =
    Glide.with(this)
        .load(imgUrl)
        .placeholder(placeholderRes)
        .error(errorRes)
        .into(this)

fun ImageView.loadCircular(imgUrl: String?,@DrawableRes errorRes : Int = R.drawable.ic_error) {

    val circularPlaceholder = GradientDrawable()
    circularPlaceholder.shape = GradientDrawable.OVAL
    circularPlaceholder.setColor(Color.LTGRAY)

    Glide.with(this)
        .load(imgUrl)
        .placeholder(circularPlaceholder)
        .error(errorRes)
        .circleCrop().into(this)
}
