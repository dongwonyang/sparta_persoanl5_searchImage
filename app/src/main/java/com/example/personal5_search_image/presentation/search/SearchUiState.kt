package com.example.personal5_search_image.presentation.search

data class SearchUiState(
    val list: List<SearchListItem>,
    val isLoading: Boolean
) {
    companion object{
        fun init(): SearchUiState = SearchUiState(
            list= emptyList(),
            isLoading = false
        )
    }
}