package com.monir.marvelapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monir.marvelapp.base.BaseResource
import com.monir.marvelapp.data.model.Comic
import com.monir.marvelapp.data.model.Event
import com.monir.marvelapp.data.model.Series
import com.monir.marvelapp.data.model.Story
import com.monir.marvelapp.data.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val repository: MarvelRepository) : ViewModel() {

    private val _comicsResource = MutableLiveData<BaseResource<Comic>>()
    val comicsResource: LiveData<BaseResource<Comic>> get() = _comicsResource

    private val _eventsResource = MutableLiveData<BaseResource<Event>>()
    val eventsResource: LiveData<BaseResource<Event>> get() = _eventsResource

    private val _storiesResource = MutableLiveData<BaseResource<Story>>()
    val storiesResource: LiveData<BaseResource<Story>> get() = _storiesResource

    private val _seriesResource = MutableLiveData<BaseResource<Series>>()
    val seriesResource: LiveData<BaseResource<Series>> get() = _seriesResource

    fun getCharacterDetails(characterId: Int) {
        getComics(characterId)
        getEvents(characterId)
        getStories(characterId)
        getSeries(characterId)
    }

    fun getComics(characterId: Int) {
        viewModelScope.launch {
            _comicsResource.postValue(BaseResource.Loading())
            when (val result = repository.getCharacterComics(characterId,0,3)) {
                is BaseResource.Success -> {
                    _comicsResource.postValue(BaseResource.Success(result.data!!))
                }
                is BaseResource.Error -> _comicsResource.postValue(result)
            }
        }
    }

    fun getEvents(characterId: Int) {
        viewModelScope.launch {
            _eventsResource.postValue(BaseResource.Loading())
            when (val result = repository.getCharacterEvents(characterId,0,3)) {
                is BaseResource.Success -> {
                    _eventsResource.postValue(BaseResource.Success(result.data!!))
                }
                is BaseResource.Error -> _eventsResource.postValue(result)
            }
        }
    }

    fun getStories(characterId: Int) {
        viewModelScope.launch {
            _storiesResource.postValue(BaseResource.Loading())
            when (val result = repository.getCharacterStories(characterId,0,3)) {
                is BaseResource.Success -> {
                    _storiesResource.postValue(BaseResource.Success(result.data!!))
                }
                is BaseResource.Error -> _storiesResource.postValue(result)
            }
        }
    }

    fun getSeries(characterId: Int) {
        viewModelScope.launch {
            _seriesResource.postValue(BaseResource.Loading())
            when (val result = repository.getCharacterSeries(characterId,0,3)) {
                is BaseResource.Success -> {
                    _seriesResource.postValue(BaseResource.Success(result.data!!))
                }
                is BaseResource.Error -> _seriesResource.postValue(result)
            }
        }
    }

}
