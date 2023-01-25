package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.SongFeed
import com.google.gson.annotations.SerializedName

@Keep
data class SongFeedDto(
    @SerializedName("author")
    val author: AuthorDto,
    @SerializedName("entry")
    val entry: List<EntryDto>,
    @SerializedName("updated")
    val updated: LabelDto,
    @SerializedName("rights")
    val rights: LabelDto,
    @SerializedName("title")
    val title: LabelDto,
    @SerializedName("icon")
    val icon: LabelDto,
    @SerializedName("link")
    val link: List<LinkDto>,
    @SerializedName("id")
    val id: LabelDto,
)

fun SongFeedDto.toSongFeed(): SongFeed {
    return SongFeed(
        author = author.toAuthor(),
        entry = entry.map { it.toEntry() },
        updated = updated.toLabel(),
        rights = rights.toLabel(),
        title = title.toLabel(),
        icon = icon.toLabel(),
        link = link.map { it.toLink() },
        id = id.toLabel(),
    )
}