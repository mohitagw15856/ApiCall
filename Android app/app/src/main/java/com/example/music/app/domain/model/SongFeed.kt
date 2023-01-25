package com.example.music.app.domain.model

data class SongFeed(
    val author: Author,
    val entry: List<Entry>,
    val updated: Label,
    val rights: Label,
    val title: Label,
    val icon: Label,
    val link: List<Link>,
    val id: Label,

    )