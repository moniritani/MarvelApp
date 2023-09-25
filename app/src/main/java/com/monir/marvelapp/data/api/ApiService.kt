package com.monir.marvelapp.data.api

import com.monir.marvelapp.base.BaseResource
import com.monir.marvelapp.data.model.Character
import com.monir.marvelapp.data.model.Comic
import com.monir.marvelapp.data.model.Event
import com.monir.marvelapp.data.model.Series
import com.monir.marvelapp.data.model.Story
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService
{
    @GET(ApiVariables.Url.GET_CHARACTERS)
    suspend fun getCharacters(): Response<BaseResource<Character>>

    @GET(ApiVariables.Url.GET_CHARACTER)
    suspend fun getCharacter(@Path(ApiVariables.Parameters.CHARACTER_ID) id: Int): Response<BaseResource<Character>>

    @GET(ApiVariables.Url.GET_CHARACTER_COMICS)
    suspend fun getCharacterComics(@Path(ApiVariables.Parameters.CHARACTER_ID) id: Int): Response<BaseResource<Comic>>

    @GET(ApiVariables.Url.GET_CHARACTER_STORIES)
    suspend fun getCharacterStories(@Path(ApiVariables.Parameters.CHARACTER_ID) id: Int): Response<BaseResource<Story>>

    @GET(ApiVariables.Url.GET_CHARACTER_EVENTS)
    suspend fun getCharacterEvents(@Path(ApiVariables.Parameters.CHARACTER_ID) id: Int): Response<BaseResource<Event>>

    @GET(ApiVariables.Url.GET_CHARACTER_SERIES)
    suspend fun getCharacterSeries(@Path(ApiVariables.Parameters.CHARACTER_ID) id: Int): Response<BaseResource<Series>>

}