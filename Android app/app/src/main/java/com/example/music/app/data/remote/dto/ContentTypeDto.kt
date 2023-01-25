package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.ContentType
import com.example.music.app.domain.model.ContentType2
import com.google.gson.annotations.SerializedName

@Keep
data class ContentTypeDto(
    @SerializedName("im:contentType")
    val imContentType: ContentType2Dto,
    @SerializedName("attributes")
    val attributes: ContentTypeAttributeDto
)

@Keep
data class ContentType2Dto(
    @SerializedName("attributes")
    val attributes: ContentTypeAttributeDto
)

fun ContentTypeDto.toContentType(): ContentType {
    return ContentType(
        imContentType = imContentType.toContentType2(),
        attributes = attributes.toContentTypeAttribute()
    )
}

fun ContentType2Dto.toContentType2(): ContentType2 {
    return ContentType2(
        attributes = attributes.toContentTypeAttribute()
    )
}