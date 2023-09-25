package com.monir.marvelapp.data.model

import java.util.Date

data class Story(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val type: String?,
    val modified: Date?,
    val thumbnail: Thumbnail?,
    val comics: Resource?,
    val series: Series?,
    val events: Resource?,
    val characters: Resource?,
    val creators: Resource?,
    val originalIssue: ResourceItem?
)