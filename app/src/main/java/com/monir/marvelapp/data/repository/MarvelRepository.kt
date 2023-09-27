package com.monir.marvelapp.data.repository

import com.monir.marvelapp.base.BaseResource
import com.monir.marvelapp.base.ErrorType
import com.monir.marvelapp.data.api.ApiService
import com.monir.marvelapp.data.api.ApiVariables
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

    suspend fun getCharacters(offset: Int = 0,limit: Int = ApiVariables.DEFAULT_ITEMS_LIMIT): BaseResource<Character> {
        return safeApiCall { apiService.getCharacters(offset,limit) }
    }

    suspend fun getCharacter(characterID: Int): BaseResource<Character> {
        return safeApiCall { apiService.getCharacter(characterID) }
    }

    suspend fun getCharacterStories(characterID: Int,offset: Int = 0,limit: Int = ApiVariables.DEFAULT_ITEMS_LIMIT): BaseResource<Story> {
        return safeApiCall { apiService.getCharacterStories(characterID,offset,limit) }
    }

    suspend fun getCharacterEvents(characterID: Int,offset: Int = 0,limit: Int = ApiVariables.DEFAULT_ITEMS_LIMIT): BaseResource<Event> {
        return safeApiCall { apiService.getCharacterEvents(characterID,offset,limit) }
    }

    suspend fun getCharacterComics(characterID: Int,offset: Int = 0,limit: Int = ApiVariables.DEFAULT_ITEMS_LIMIT): BaseResource<Comic> {
        return safeApiCall { apiService.getCharacterComics(characterID,offset,limit) }
    }

    suspend fun getCharacterSeries(characterID: Int,offset: Int = 0,limit: Int = ApiVariables.DEFAULT_ITEMS_LIMIT): BaseResource<Series> {
        return safeApiCall { apiService.getCharacterSeries(characterID,offset,limit) }
    }
}
