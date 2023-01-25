package com.example.music.app.presentation.song_list.components


sealed class NavigationItem(var route: String) {
    object Home : NavigationItem("hom")
}