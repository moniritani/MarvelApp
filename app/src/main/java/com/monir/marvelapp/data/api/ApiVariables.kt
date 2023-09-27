package com.monir.marvelapp.data.api

object ApiVariables {

    const val DEFAULT_ITEMS_LIMIT = 10

    object Parameters
    {
        const val CHARACTER_ID   = "characterID"
        const val TIME_STAMP     = "ts"
        const val API_KEY        = "apikey"
        const val HASH           = "hash"
        const val LIMIT          = "limit"
        const val OFFSET         = "offset"
    }
    object Url
    {
        const val GET_CHARACTERS        = "characters"
        const val GET_CHARACTER         = "characters/{${Parameters.CHARACTER_ID}}"
        const val GET_CHARACTER_COMICS  = "characters/{${Parameters.CHARACTER_ID}}/comics"
        const val GET_CHARACTER_STORIES = "characters/{${Parameters.CHARACTER_ID}}/events"
        const val GET_CHARACTER_SERIES  = "characters/{${Parameters.CHARACTER_ID}}/series"
        const val GET_CHARACTER_EVENTS  = "characters/{${Parameters.CHARACTER_ID}}/events"
    }
}