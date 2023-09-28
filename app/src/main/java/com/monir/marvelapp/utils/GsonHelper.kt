package com.monir.marvelapp.utils

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


enum class GsonHelper {
    INSTANCE;

    private val gsonSneakCase: Gson by lazy {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .create()
    }

    companion object {
        fun forSneak(): Gson = INSTANCE.gsonSneakCase
    }

    inner class DateDeserializer : JsonDeserializer<Date?> {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
        override fun deserialize(json: JsonElement?,typeOfT: Type?,context: JsonDeserializationContext?): Date? {
            return try {
                if (json?.asString != null) dateFormat.parse(json.asString)
                else null
            } catch (e: ParseException) {
                // log the exception and return null
                null
            }
        }
    }
}
