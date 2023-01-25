package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.Artist
import com.example.music.app.domain.model.AttributesArtist
import com.google.gson.annotations.SerializedName

@Keep
data class ArtistDto(
    @SerializedName("label")
    val label: String,
    @SerializedName("attributes")
    val attributes: AttributesArtistDto?
)

@Keep
data class AttributesArtistDto(
    @SerializedName("href")
    val href: String
)

fun ArtistDto.toArtist(): Artist {
    return Artist(
        label = label,
        attributes = attributes?.toAttributesArtist() ?: AttributesArtist("")
    )
}

fun AttributesArtistDto.toAttributesArtist(): AttributesArtist {
    return AttributesArtist(
        href = href
    )
}