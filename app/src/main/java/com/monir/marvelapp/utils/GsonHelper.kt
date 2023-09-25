package com.monir.marvelapp.utils

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

enum class GsonHelper {
    INSTANCE;

    private val gsonCamelCase = Gson()
    private val gsonSneakCase = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    companion object {
        fun forCamel(): Gson = INSTANCE.gsonCamelCase
        fun forSneak(): Gson = INSTANCE.gsonSneakCase

    }
}
