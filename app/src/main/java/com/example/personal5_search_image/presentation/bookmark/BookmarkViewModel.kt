package com.example.personal5_search_image.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookmarkViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BookmarkUiState.init())
    val uiState: StateFlow<BookmarkUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<BookmarkListEvent>()
    val event : SharedFlow<BookmarkListEvent> = _event.asSharedFlow()

    fun onUpdateBookmark(list: List<BookmarkListItem>) {
        _uiState.update { prev ->
            prev.copy(
                list = list
            )
        }
    }

    fun onClickBookmark(item: BookmarkListItem) = viewModelScope.launch {
        val list = uiState.value.list.toMutableList()
        val position = list.indexOfFirst { item.id == it.id }
        _uiState.update { prev ->
            prev.copy(
                list = prev.list.toMutableList().also {
                    it[position] = item.copy(isBookmarked = !item.isBookmarked)
                }
            )
        }

        _event.emit(BookmarkListEvent.DeleteBookmark(uiState.value.list))
    }


    fun showLoading(isLoading: Boolean) {
        _uiState.update { prev ->
            prev.copy(
                isLoading = isLoading
            )
        }
    }
}

class BookmarkViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = BookmarkViewModel() as T
}
