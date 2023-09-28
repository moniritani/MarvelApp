package com.monir.marvelapp.ui.characters

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monir.marvelapp.base.BaseResource
import com.monir.marvelapp.data.model.Character
import com.monir.marvelapp.data.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: MarvelRepository,
                                              private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var charactersList = ArrayList<Character>()
    private val _charactersResource = MutableLiveData<BaseResource<Character>>()
    val charactersResource: LiveData<BaseResource<Character>> get() = _charactersResource

    private var offset = 0 // start offset
    private val limit = 20 // number of items to be fetched in one request
    private var isLastPage = false // whether all pages have been loaded
    private var isLoading = false // whether new page is being loaded

    init {
        loadCharacters()
    }

    fun loadMoreCharacters(){
        if (isLoading || isLastPage) return
        loadCharacters()
    }

    private fun loadCharacters() {

        viewModelScope.launch {
            _charactersResource.value = BaseResource.Loading()
            isLoading = true

            val result = repository.getCharacters(offset, limit)
            isLoading = false
            when (result) {
                is BaseResource.Success -> {
                    val baseResult = result.data!!
                    with(baseResult){
                        data?.let { newData ->
                            charactersList.addAll(newData)
                        }
                        data = charactersList
                    }
                    _charactersResource.value = BaseResource.Success(baseResult)
                    offset = charactersList.size
                    if (offset >= result.data.total) isLastPage = true
                }
                is BaseResource.Error -> _charactersResource.value = result
            }
        }
    }

    var recyclerSavedViewState: Parcelable?
        get() = savedStateHandle[RECYCLER_VIEW_SAVED_STATE]
        set(value) { savedStateHandle[RECYCLER_VIEW_SAVED_STATE] = value }

    companion object {
        private const val RECYCLER_VIEW_SAVED_STATE = "RECYCLER_VIEW_SAVED_STATE"
    }
}
