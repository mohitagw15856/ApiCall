package com.example.music.app.presentation

sealed class ScreenType(val route: String) {
    object SongListScreen : ScreenType("song_list_screen")
}