package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.ContentTypeAttribute
import com.google.gson.annotations.SerializedName

@Keep
data class ContentTypeAttributeDto(
    @SerializedName("term")
    val term: String,
    @SerializedName("label")
    val label: String,
)

fun ContentTypeAttributeDto.toContentTypeAttribute(): ContentTypeAttribute {
    return ContentTypeAttribute(
        term = term,
        label = label
    )
}