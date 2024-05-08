package com.example.personal5_search_image.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookmarkViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(BookmarkUiState.init())
    val uiState: StateFlow<BookmarkUiState> = _uiState.asStateFlow()

    fun onUpdateBookmark(list: List<BookmarkListItem>){
        _uiState.update {prev->
            prev.copy(
                list = list
            )
        }
    }

    fun showLoading(isLoading: Boolean){
        _uiState.update { prev->
            prev.copy(
                isLoading = isLoading
            )
        }
    }
}

class BookmarkViewModelFactory: ViewModelProvider.Factory{
    override fun <T : ViewModel> create(
        modelClass: Class<T>): T = BookmarkViewModel() as T
}
