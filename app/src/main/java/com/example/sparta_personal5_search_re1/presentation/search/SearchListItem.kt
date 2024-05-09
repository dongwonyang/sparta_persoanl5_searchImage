package com.example.sparta_personal5_search_re1.presentation.search

import java.util.Date

data class SearchListItem (
    val thumbnailUrl: String?,
    val siteName: String?,
    val dateTime: Date?,
    val docUrl: String?,
    val isBookmarked: Boolean?
)