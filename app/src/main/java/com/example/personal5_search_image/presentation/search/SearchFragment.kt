package com.example.personal5_search_image.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.personal5_search_image.presentation.shared.SharedViewModel
import com.example.personal5search_image.databinding.FragmentSearchImageBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private var searchKey: String? = null
    private var _binding: FragmentSearchImageBinding? = null
    private val binding: FragmentSearchImageBinding get() = _binding!!


    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }

    private val listAdapter: SearchListAdapter by lazy {
        SearchListAdapter(
            onBookmark = { item ->
                viewModel.onBookmark(item)
            }
        )
    }

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            searchKey = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()

        searchKey?.let { viewModel.onSearch(it) }


    }

    private fun initView() = with(binding) {
        rvSearchImage.adapter = listAdapter
        rvSearchImage.layoutManager = GridLayoutManager(requireContext(), 2)
    }


    private fun initViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            uiState.flowWithLifecycle(lifecycle)
                .collectLatest { state ->
                    onBind(state)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            event.flowWithLifecycle(lifecycle)
                .collectLatest { event ->
                    onEvent(event)
                }
        }
    }

    private fun onBind(state: SearchUiState) = with(binding) {
        progress.isVisible = state.isLoading
        listAdapter.submitList(state.list)
    }


    private fun onEvent(event: SearchListEvent) {
        when(event){
            is SearchListEvent.UpdateBookmark -> sharedViewModel.updateBookmarkItems(event.list)
        }
    }

    fun search(searchKey: String){
        viewModel.onSearch(searchKey)
    }

    companion object {
        const val ARG_PARAM = "SEARCH_KEY"

        fun newInstance(searchKey: String)
                : SearchFragment = SearchFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM, searchKey)
            }
        }
    }
}