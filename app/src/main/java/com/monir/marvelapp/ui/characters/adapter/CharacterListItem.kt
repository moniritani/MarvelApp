package com.monir.marvelapp.ui.characters.adapter

import com.monir.marvelapp.data.model.Character

sealed class CharacterListItem {
    data class CharacterItem(val character: Character) : CharacterListItem()
    data object LoadingItem : CharacterListItem()
}