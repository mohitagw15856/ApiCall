package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.Image
import com.google.gson.annotations.SerializedName

@Keep
data class ImageDto(
    @SerializedName("label")
    val label: String,
    @SerializedName("attributes")
    val attributes: ImageAttributeDto
)

fun ImageDto.toImage(): Image {
    return Image(
        label = label,
        attributes = attributes.toImageAttribute()
    )
}