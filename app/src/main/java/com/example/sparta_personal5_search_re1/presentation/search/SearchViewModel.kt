package com.example.sparta_personal5_search_re1.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sparta_personal5_search_re1.network.client.RetrofitClient
import com.example.sparta_personal5_search_re1.network.data.model.SearchImageDocumentEntity
import com.example.sparta_personal5_search_re1.network.data.repository.SearchRepository
import com.example.sparta_personal5_search_re1.network.data.repository.SearchRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class SearchViewModel(
    private val search: SearchRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState.init())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onSearch(searchKey: String) = viewModelScope.launch {
        showLoading(true)

        val item = createItems(
            search.getSearchImage(searchKey).documents ?: emptyList()
        )

        _uiState.update { prev ->
            prev.copy(
                list = item
            )
        }

        showLoading(false)
    }


    private fun createItems(
        images: List<SearchImageDocumentEntity>
    ): List<SearchListItem> {

        fun createImageItem(
            list: List<SearchImageDocumentEntity>
        ): List<SearchListItem> = list.map {
            SearchListItem(
                thumbnailUrl = it.thumbnailUrl,
                siteName = it.displaySiteName,
                dateTime = it.dateTime,
                docUrl = it.docUrl,
                isBookmarked = false
            )
        }

        return arrayListOf<SearchListItem>().apply {
            addAll(createImageItem(images))
        }.sortedByDescending {
            it.dateTime
        }
    }

    private fun showLoading(isLoading: Boolean) {
        _uiState.update { prev ->
            prev.copy(
                isLoading = isLoading
            )
        }
    }
}


class SearchViewModelFactory : ViewModelProvider.Factory {
    private val repository = SearchRepositoryImpl(RetrofitClient.search)
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = SearchViewModel(repository) as T
}