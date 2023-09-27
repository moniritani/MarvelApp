package com.monir.marvelapp.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monir.marvelapp.base.BaseResource
import com.monir.marvelapp.data.model.Character
import com.monir.marvelapp.data.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: MarvelRepository) : ViewModel() {

    private val _charactersResource = MutableLiveData<BaseResource<Character>>()
    val charactersResource: LiveData<BaseResource<Character>> get() = _charactersResource

    private var offset = 0 // start offset
    private val limit = 20 // number of items to be fetched in one request
    private var isLastPage = false // whether all pages have been loaded
    private var isLoading = false // whether new page is being loaded

    init {
        getCharacters()
    }

    fun loadMoreCharacters() {
        if(isLoading || isLastPage) return
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
             _charactersResource.postValue(BaseResource.Loading())
            isLoading = true
            when (val result = repository.getCharacters(offset, limit)) {
                is BaseResource.Success -> {
                    val data = result.data?.data ?: emptyList()
                    _charactersResource.postValue(BaseResource.Success(result.data!!))
                    offset += data.size
                    if (offset >= result.data.total) isLastPage = true
                }
                is BaseResource.Error -> _charactersResource.postValue(result)
            }
            isLoading = false
        }
    }
}
