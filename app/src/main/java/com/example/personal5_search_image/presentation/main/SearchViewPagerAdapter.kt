package com.example.personal5_search_image.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.personal5_search_image.presentation.bookmark.BookmarkFragment
import com.example.personal5_search_image.presentation.search.SearchFragment

class SearchViewPagerAdapter(
    fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity){

    private val fragments = mutableListOf<Fragment>(
        SearchFragment(),
        BookmarkFragment()
    )

    fun getTabTitle() = listOf<String>(
        "검색",
        "즐겨찾기"
    )

    fun search(searchKey: String){
        (fragments[0] as SearchFragment).search(searchKey)
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}