package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.Attributes
import com.example.music.app.domain.model.Link
import com.google.gson.annotations.SerializedName

@Keep
data class LinkDto(
    @SerializedName("attributes")
    val attributes: AttributesDto?
)

@Keep
data class AttributesDto(
    @SerializedName("rel")
    val rel: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("href")
    val href: String,
)

fun LinkDto.toLink(): Link {
    return Link(
        attributes = attributes?.toAttributes() ?: Attributes("", "", "")
    )
}

fun AttributesDto.toAttributes(): Attributes {
    return Attributes(
        rel = rel,
        type = type,
        href = href
    )
}