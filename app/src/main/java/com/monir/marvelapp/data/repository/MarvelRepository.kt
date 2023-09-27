package com.monir.marvelapp.data.repository

import com.monir.marvelapp.base.BaseResource
import com.monir.marvelapp.base.ErrorType
import com.monir.marvelapp.data.api.ApiService
import com.monir.marvelapp.data.model.Character
import com.monir.marvelapp.data.model.Comic
import com.monir.marvelapp.data.model.Event
import com.monir.marvelapp.data.model.Series
import com.monir.marvelapp.data.model.Story
import retrofit2.Response
import javax.inject.Inject

class MarvelRepository @Inject constructor(private val apiService: ApiService) {

    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<BaseResource<T>>): BaseResource<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.code == 200 && body.data != null) BaseResource.Success(body.data)
                else BaseResource.Error(ErrorType.ApiError(response.code(), response.message()))
            } else {
                BaseResource.Error(ErrorType.ApiError(response.code(), response.message()))
            }
        } catch (e: Exception) {
            BaseResource.Error(ErrorType.NetworkError)
        }
    }

    suspend fun getCharacters(): BaseResource<Character> {
        return safeApiCall { apiService.getCharacters() }
    }

    suspend fun getCharacter(characterID: Int): BaseResource<Character> {
        return safeApiCall { apiService.getCharacter(characterID) }
    }

    suspend fun getCharacterStories(characterID: Int): BaseResource<Story> {
        return safeApiCall { apiService.getCharacterStories(characterID) }
    }

    suspend fun getCharacterEvents(characterID: Int): BaseResource<Event> {
        return safeApiCall { apiService.getCharacterEvents(characterID) }
    }

    suspend fun getCharacterComics(characterID: Int): BaseResource<Comic> {
        return safeApiCall { apiService.getCharacterComics(characterID) }
    }

    suspend fun getCharacterSeries(characterID: Int): BaseResource<Series> {
        return safeApiCall { apiService.getCharacterSeries(characterID) }
    }
}
