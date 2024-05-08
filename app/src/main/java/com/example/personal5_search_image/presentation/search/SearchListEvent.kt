package com.example.personal5_search_image.presentation.search

sealed interface SearchListEvent {
    data class UpdateBookmark(
        val list: List<SearchListItem>
    ): SearchListEvent
}