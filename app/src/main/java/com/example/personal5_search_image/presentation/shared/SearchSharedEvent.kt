package com.example.personal5_search_image.presentation.shared

import com.example.personal5_search_image.presentation.bookmark.BookmarkListItem
import com.example.personal5_search_image.presentation.search.SearchListItem

sealed interface SearchSharedEvent {
    data class UpdateBookmark(
        val list: List<BookmarkListItem>
    ): SearchSharedEvent

    data class DeleteBookmark( // bookmark에서 삭제 시 search에 전달
        val list: List<SearchListItem>
    ): SearchSharedEvent
}