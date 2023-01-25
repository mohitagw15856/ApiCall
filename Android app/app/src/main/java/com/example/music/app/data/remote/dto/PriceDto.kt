package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.Price
import com.google.gson.annotations.SerializedName

@Keep
data class PriceDto(
    @SerializedName("label")
    val label: String,
    @SerializedName("attributes")
    val attributes: PriceAttributeDto
)

fun PriceDto.toPrice(): Price {
    return Price(
        label = label,
        attributes = attributes.toPriceAttribute()
    )
}