package com.example.personal5_search_image.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.personal5_search_image.presentation.search.SearchFragment
import com.example.personal5search_image.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewPagerAdapter: SearchViewPagerAdapter by lazy {
        SearchViewPagerAdapter(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        initView()
    }

    private fun initView() = with(binding) {
        viewPagerMain.adapter = viewPagerAdapter
        viewPagerMain.isUserInputEnabled = true
        viewPagerMain.offscreenPageLimit= viewPagerAdapter.itemCount

        val tabText = viewPagerAdapter.getTabTitle()
        TabLayoutMediator(tabLayoutMain, viewPagerMain) { tab, position ->
            tab.text = tabText[position]
        }.attach()

        btnSearch.setOnClickListener {
            viewPagerAdapter.search(etSearch.text.toString())
        }

        etSearch.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                btnSearch.performClick()
                true
            } else{
                false
            }
        }
    }

//    private fun setFragment(fragment: Fragment) {
//        this@MainActivity.supportFragmentManager.beginTransaction()
//            .replace(binding.frameLayoutMain.id, fragment)
//            .addToBackStack(null)
//            .commit()
//    }
}