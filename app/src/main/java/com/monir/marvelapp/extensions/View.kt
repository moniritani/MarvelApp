package com.monir.marvelapp.extensions

import android.view.View

fun View.hide() { this.visibility = View.GONE}
fun View.hideWithSpace() { this.visibility = View.INVISIBLE}
fun View.show() { this.visibility = View.VISIBLE}