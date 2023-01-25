package com.example.music.app.domain.model

data class Category(
    val attributes: AttributesCategory
)

data class AttributesCategory(
    val imId: String,
    val term: String,
    val scheme: String,
    val label: String,
)