package com.monir.marvelapp.data.model
import com.monir.marvelapp.data.model.helpers.UrlItem
import java.util.Date

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: Date?,
    val resourceURI: String?,
    val urls: List<UrlItem>?,
    val thumbnail: Thumbnail?,
    val comics: Resource?,
    val stories: Resource?,
    val events: Resource?,
    val series: Resource?
)