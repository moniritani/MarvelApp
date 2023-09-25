package com.monir.marvelapp.data.model

import com.monir.marvelapp.data.model.helpers.DateItem
import com.monir.marvelapp.data.model.helpers.ImageItem
import com.monir.marvelapp.data.model.helpers.Price
import com.monir.marvelapp.data.model.helpers.TextObject
import com.monir.marvelapp.data.model.helpers.UrlItem
import java.util.Date

data class Comic(
    val id: Int?,
    val digitalId: Int?,
    val title: String?,
    val issueNumber: Double?,
    val variantDescription: String?,
    val description: String?,
    val modified: Date?,
    val isbn: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format: String?,
    val pageCount: Int?,
    val textObjects: List<TextObject>?,
    val resourceURI: String?,
    val urls: List<UrlItem>?,
    val series: ResourceItem?,
    val variants: List<ResourceItem>?,
    val collections: List<ResourceItem>?,
    val collectedIssues: List<ResourceItem>?,
    val dates: List<DateItem>?,
    val prices: List<Price>?,
    val thumbnail: Thumbnail?,
    val images: List<ImageItem>?,
    val creators: Resource?,
    val characters: Resource?,
    val stories: Resource?,
    val events: Resource?
)