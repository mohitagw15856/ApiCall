package com.example.music.app.domain.model

data class Link(
    val attributes: Attributes
)

data class Attributes(
    val rel: String?,
    val type: String?,
    val href: String?,
)