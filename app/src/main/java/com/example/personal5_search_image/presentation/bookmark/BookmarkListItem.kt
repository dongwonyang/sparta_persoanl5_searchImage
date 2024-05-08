package com.example.personal5_search_image.presentation.bookmark

import java.util.Date

data class BookmarkListItem(
    val id: String,
    val displaySiteName: String,
    val dateTime: Date,
    val docUrl: String,
    val thumbnailUrl: String,
    val isBookmarked: Boolean
)