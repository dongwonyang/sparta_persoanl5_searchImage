package com.example.personal5_search_image.presentation.search

import java.util.Date
import java.util.UUID


sealed interface SearchListItem {
    val id: String
    val thumbnailUrl: String
    val displaySiteName: String
    val dateTime: Date
    val docUrl: String
    val isBookmarked: Boolean
    data class ImageItem(
        override val id: String = UUID.randomUUID().toString(),
        override val displaySiteName: String,
        override val dateTime: Date,
        override val docUrl: String,
        override val thumbnailUrl: String,
        override val isBookmarked: Boolean = false
    ): SearchListItem
}