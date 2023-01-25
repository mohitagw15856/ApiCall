package com.example.music.app.domain.model

data class EntryId(
    val label: String,
    val attributes: AttributesId
)

data class AttributesId(
    val imId: String
)