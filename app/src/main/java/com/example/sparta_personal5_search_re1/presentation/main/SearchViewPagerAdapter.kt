package com.example.sparta_personal5_search_re1.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sparta_personal5_search_re1.presentation.search.SearchFragment

class SearchViewPagerAdapter(
  fragmentActivity: FragmentActivity
) :FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf<SearchFragmentItem>(
        SearchFragmentItem(SearchFragment(), "검색")
    )
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }

    fun getTitle(position: Int): String{
        return if(position >= fragments.size) "" else fragments[position].title
    }

    fun onSearch(searchKey: String){
        (fragments[0].fragment as SearchFragment).onSearch(searchKey)
    }
}