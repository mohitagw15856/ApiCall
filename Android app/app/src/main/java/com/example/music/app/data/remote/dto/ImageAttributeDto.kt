package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.ImageAttribute
import com.google.gson.annotations.SerializedName

@Keep
data class ImageAttributeDto(
    @SerializedName("height")
    val height: String
)

fun ImageAttributeDto.toImageAttribute(): ImageAttribute {
    return ImageAttribute(
        height = height
    )
}