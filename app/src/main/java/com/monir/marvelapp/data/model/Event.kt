package com.monir.marvelapp.data.model

import com.monir.marvelapp.data.model.helpers.UrlItem
import java.util.Date

data class Event(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<UrlItem>?,
    val modified: Date?,
    val start: Date?,
    val end: Date?,
    val thumbnail: Thumbnail?,
    val comics: Resource?,
    val stories: Resource?,
    val series: Resource?,
    val characters: Resource?,
    val creators: Resource?,
    val next: ResourceItem?,
    val previous: ResourceItem?
)