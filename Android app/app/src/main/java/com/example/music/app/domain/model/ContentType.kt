package com.example.music.app.domain.model

import com.google.gson.annotations.SerializedName

data class ContentType(
    @SerializedName("im:contentType")
    val imContentType: ContentType2,
    val attributes: ContentTypeAttribute
)

data class ContentType2(
    val attributes: ContentTypeAttribute
)