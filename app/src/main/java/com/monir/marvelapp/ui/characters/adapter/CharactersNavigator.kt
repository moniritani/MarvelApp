package com.monir.marvelapp.ui.characters.adapter

import com.monir.marvelapp.data.model.Character

fun interface CharactersNavigator {
    fun onClick(character: Character)
}