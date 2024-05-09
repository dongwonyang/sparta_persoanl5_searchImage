package com.example.personal5_search_image.presentation.bookmark

sealed interface BookmarkListEvent {
    data class DeleteBookmark(
        val list: List<BookmarkListItem>
    ): BookmarkListEvent
}