package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.ReleasedDate
import com.google.gson.annotations.SerializedName

@Keep
data class ReleasedDateDto(
    @SerializedName("label")
    val label: String,
    @SerializedName("attributes")
    val attributes: LabelDto
)

fun ReleasedDateDto.toReleasedDate(): ReleasedDate {
    return ReleasedDate(
        label = label,
        attributes = attributes.toLabel()
    )
}