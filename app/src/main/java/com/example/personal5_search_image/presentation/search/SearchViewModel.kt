package com.example.personal5_search_image.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.personal5_search_image.network.RetrofitClient
import com.example.personal5_search_image.network.data.model.entity.SearchImageEntity
import com.example.personal5_search_image.network.data.repository.SearchRepository
import com.example.personal5_search_image.network.data.repository.SearchRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState.init())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SearchListEvent>()
    val event: SharedFlow<SearchListEvent> = _event.asSharedFlow()
    fun onSearch(
        query: String
    ) {
        viewModelScope.launch {
            showLoading(true)
            runCatching {
                val item: List<SearchListItem> = createItem(
                    searchRepository.getSearchImage(query)
                )

                _uiState.update { prev ->
                    prev.copy(
                        list = item
                    )
                }

            }.onFailure {
                // network, error, ...
                Log.e("loading fail", it.message.toString())
            }
            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        _uiState.update { prev ->
            prev.copy(
                isLoading = isLoading
            )
        }
    }

    private fun createItem(images: SearchImageEntity): List<SearchListItem> {

        fun createImageImage(
            images: SearchImageEntity
        ): List<SearchListItem.ImageItem> = images.documents.map { document ->
            SearchListItem.ImageItem(
                thumbnailUrl = document.thumbnailUrl,
                displaySiteName = document.displaySiteName,
                dateTime = document.dateTime,
                docUrl = document.docUrl
            )
        }

        return arrayListOf<SearchListItem>().apply {
            addAll(createImageImage(images))
        }.sortedByDescending {
            it.dateTime
        }
    }

    fun onBookmark(
        item: SearchListItem
    ) = viewModelScope.launch {
        val mutableList = uiState.value.list.toMutableList()
        val position = mutableList.indexOfFirst {
            it.id == item.id
        }
        _uiState.update { prev ->
            prev.copy(
                list = mutableList.also {
                    it[position] = when (item) {
                        is SearchListItem.ImageItem -> {
                            item.copy(isBookmarked = !item.isBookmarked)
                        }
                    }
                }
            )
        }
        _event.emit(SearchListEvent.UpdateBookmark(uiState.value.list))
    }
}

class SearchViewModelFactory() : ViewModelProvider.Factory {
    private val repository = SearchRepositoryImpl(RetrofitClient.search)

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = SearchViewModel(repository) as T
}

