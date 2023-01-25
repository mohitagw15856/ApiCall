package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.Label
import com.google.gson.annotations.SerializedName

@Keep
data class LabelDto(
    @SerializedName("label")
    val label: String
)

fun LabelDto.toLabel(): Label {
    return Label(
        label = label
    )
}