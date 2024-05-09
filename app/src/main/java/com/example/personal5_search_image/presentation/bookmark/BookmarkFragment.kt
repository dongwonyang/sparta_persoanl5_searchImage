package com.example.personal5_search_image.presentation.bookmark

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.personal5_search_image.presentation.shared.SearchSharedEvent
import com.example.personal5_search_image.presentation.shared.SharedViewModel
import com.example.personal5search_image.databinding.FragmentBookmarkBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BookmarkFragment : Fragment() {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding: FragmentBookmarkBinding get() = _binding!!
    private val adapter: BookmarkListAdapter by lazy {
        BookmarkListAdapter(
            onBookmark = {item ->
                viewModel.onClickBookmark(item)
            }
        )
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val viewModel: BookmarkViewModel by viewModels {
        BookmarkViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        recyclerViewBookmark.adapter = adapter
        recyclerViewBookmark.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle)
                .collectLatest { state->
                    onBind(state)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.event.flowWithLifecycle(lifecycle)
                .collectLatest { event ->
                    onEvent(event)
                }
        }



        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.event.flowWithLifecycle(lifecycle)
                .collectLatest { event ->
                    onSharedEvent(event)
                }
        }
    }

    private fun onSharedEvent(
        event: SearchSharedEvent
    ){
        when(event){
            is SearchSharedEvent.UpdateBookmark -> viewModel.onUpdateBookmark(event.list)
            is SearchSharedEvent.DeleteBookmark -> {}
        }
    }


    private fun onBind(state: BookmarkUiState) = with(binding){
        adapter.submitList(state.list)
        progressBookmark.isVisible = state.isLoading
    }

    private fun onEvent(event: BookmarkListEvent) = when(event){
        is BookmarkListEvent.DeleteBookmark -> sharedViewModel.deleteBookmarkItems(event.list)
    }
}