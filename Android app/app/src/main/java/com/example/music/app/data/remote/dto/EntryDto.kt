package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.Entry
import com.google.gson.annotations.SerializedName

@Keep
data class EntryDto(
    @SerializedName("im:name")
    val imName: LabelDto,
    @SerializedName("im:image")
    val imImage: List<ImageDto>,
    @SerializedName("im:itemCount")
    val imItemCnt: LabelDto,
    @SerializedName("im:price")
    val imPrice: PriceDto,
    @SerializedName("im:contentType")
    val imContentType: ContentTypeDto,
    @SerializedName("rights")
    val rights: LabelDto,
    @SerializedName("title")
    val title: LabelDto,
    @SerializedName("link")
    val link: LinkDto,
    @SerializedName("id")
    val id: EntryIdDto,
    @SerializedName("im:artist")
    val imArtist: ArtistDto,
    @SerializedName("category")
    val category: CategoryDto,
    @SerializedName("im:releaseDate")
    val imReleasedDate: ReleasedDateDto,
)

fun EntryDto.toEntry(): Entry {
    return Entry(
        imName = imName.toLabel(),
        imImage = imImage.map { it.toImage() },
        imItemCnt = imItemCnt.toLabel(),
        imPrice = imPrice.toPrice(),
        imContentType = imContentType.toContentType(),
        rights = rights.toLabel(),
        title = title.toLabel(),
        link = link.toLink(),
        id = id.toEntryId(),
        imArtist = imArtist.toArtist(),
        category = category.toCategory(),
        imReleasedDate = imReleasedDate.toReleasedDate()
    )
}