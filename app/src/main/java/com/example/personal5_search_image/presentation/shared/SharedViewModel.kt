package com.example.personal5_search_image.presentation.shared

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personal5_search_image.presentation.bookmark.BookmarkListItem
import com.example.personal5_search_image.presentation.search.SearchListItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel(){
    private val _event = MutableSharedFlow<SearchSharedEvent>()
    val event: SharedFlow<SearchSharedEvent> = _event.asSharedFlow()

    fun updateBookmarkItems(list: List<SearchListItem>) = viewModelScope.launch {
        list.filter {
            it.isBookmarked
        }.map{
            BookmarkListItem(
                id = it.id,
                displaySiteName = it.displaySiteName,
                dateTime = it.dateTime,
                docUrl = it.docUrl,
                thumbnailUrl = it.thumbnailUrl,
                isBookmarked = it.isBookmarked
            )
        }.also{
            _event.emit(SearchSharedEvent.UpdateBookmark(it))
        }
    }
}