package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.Author
import com.google.gson.annotations.SerializedName

@Keep
data class AuthorDto(
    @SerializedName("name")
    val name: LabelDto,
    @SerializedName("uri")
    val uri: LabelDto
)

fun AuthorDto.toAuthor() = Author(
    name = name.toLabel(),
    uri = uri.toLabel()
)