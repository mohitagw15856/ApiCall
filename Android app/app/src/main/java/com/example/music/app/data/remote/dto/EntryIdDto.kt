package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.AttributesId
import com.example.music.app.domain.model.EntryId
import com.google.gson.annotations.SerializedName

@Keep
data class EntryIdDto(
    @SerializedName("label")
    val label: String,
    @SerializedName("attributes")
    val attributes: AttributesIdDto
)

@Keep
data class AttributesIdDto(
    @SerializedName("im:id")
    val imId: String
)

fun EntryIdDto.toEntryId(): EntryId {
    return EntryId(
        label = label,
        attributes = attributes.toAttributesId()
    )
}

fun AttributesIdDto.toAttributesId(): AttributesId {
    return AttributesId(
        imId = imId
    )
}