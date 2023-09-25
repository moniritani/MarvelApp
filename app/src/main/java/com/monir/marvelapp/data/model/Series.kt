package com.monir.marvelapp.data.model

import com.monir.marvelapp.data.model.helpers.UrlItem
import java.util.Date

data class Series(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<UrlItem>?,
    val startYear: Int?,
    val endYear: Int?,
    val rating: String?,
    val modified: Date?,
    val thumbnail: Thumbnail?,
    val comics: Resource?,
    val stories: Resource?,
    val events: Resource?,
    val characters: Resource?,
    val creators: Resource?,
    val next: ResourceItem?,
    val previous: ResourceItem?
)