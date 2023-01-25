package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.Song
import com.google.gson.annotations.SerializedName

@Keep
data class SongDto(
    @SerializedName("feed")
    val feed: SongFeedDto? = null
)

fun SongDto?.toSong() = this?.feed?.toSongFeed()?.let {
    Song(
        feed = it
    )
}