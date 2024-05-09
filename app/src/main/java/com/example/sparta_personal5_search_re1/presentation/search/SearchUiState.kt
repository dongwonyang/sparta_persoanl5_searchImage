package com.example.sparta_personal5_search_re1.presentation.search

data class SearchUiState (
    val list: List<SearchListItem>,
    val isLoading: Boolean
){
    companion object{
        fun init() = SearchUiState(
            list= emptyList(),
            isLoading = false
        )
    }
}