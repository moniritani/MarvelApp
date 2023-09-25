package com.monir.marvelapp.data.model

data class Resource(
    val available: Int,
    val collectionURI: String,
    val items: List<ResourceItem>,
    val returned: Int
)