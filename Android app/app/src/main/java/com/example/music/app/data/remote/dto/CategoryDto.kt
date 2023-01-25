package com.example.music.app.data.remote.dto

import androidx.annotation.Keep
import com.example.music.app.domain.model.AttributesCategory
import com.example.music.app.domain.model.Category
import com.google.gson.annotations.SerializedName

@Keep
data class CategoryDto(
    @SerializedName("attributes")
    val attributes: AttributesCategoryDto
)

@Keep
data class AttributesCategoryDto(
    @SerializedName("im:id")
    val imId: String,
    @SerializedName("term")
    val term: String,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("label")
    val label: String,
)

fun CategoryDto.toCategory(): Category {
    return Category(
        attributes = attributes.toAttributesCategory()
    )
}

fun AttributesCategoryDto.toAttributesCategory(): AttributesCategory {
    return AttributesCategory(
        imId = imId,
        term = term,
        scheme = scheme,
        label = label
    )
}