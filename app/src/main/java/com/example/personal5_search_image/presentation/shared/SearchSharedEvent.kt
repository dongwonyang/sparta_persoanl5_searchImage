package com.example.personal5_search_image.presentation.shared

import com.example.personal5_search_image.presentation.bookmark.BookmarkListItem
import com.example.personal5_search_image.presentation.search.SearchListItem

sealed interface SearchSharedEvent {
    data class UpdateBookmark(
        val list: List<BookmarkListItem>
    ): SearchSharedEvent
}