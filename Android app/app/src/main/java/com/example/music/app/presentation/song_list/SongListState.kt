package com.example.music.app.presentation.song_list

import com.example.music.app.domain.model.Entry
import java.util.Collections.emptyList

data class SongListState(
    val isLoading: Boolean = false,
    val songs: List<Entry> = emptyList(),
    val error: String = ""
)
