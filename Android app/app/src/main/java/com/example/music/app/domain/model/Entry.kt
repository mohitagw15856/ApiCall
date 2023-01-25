package com.example.music.app.domain.model

data class Entry(
    val imName: Label,
    val imImage: List<Image>,
    val imItemCnt: Label,
    val imPrice: Price,
    val imContentType: ContentType,
    val rights: Label,
    val title: Label,
    val link: Link,
    val id: EntryId,
    val imArtist: Artist,
    val category: Category,
    val imReleasedDate: ReleasedDate,
)