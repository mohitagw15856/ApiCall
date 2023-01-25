package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.PriceAttribute
import com.google.gson.annotations.SerializedName

@Keep
data class PriceAttributeDto(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String,
)

fun PriceAttributeDto.toPriceAttribute(): PriceAttribute {
    return PriceAttribute(
        amount = amount,
        currency = currency
    )
}