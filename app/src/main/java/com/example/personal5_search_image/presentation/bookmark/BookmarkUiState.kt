package com.example.personal5_search_image.presentation.bookmark

data class BookmarkUiState (
    val list: List<BookmarkListItem>,
    val isLoading: Boolean
){
    companion object{
        fun init() = BookmarkUiState(
            list= emptyList(),
            isLoading = false
        )
    }
}