package com.example.sparta_personal5_search_re1.presentation.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sparta_personal5_search_re1.R
import com.example.sparta_personal5_search_re1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewPagerAdapter: SearchViewPagerAdapter by lazy {
        SearchViewPagerAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        viewPagerSearch.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayoutSearch, viewPagerSearch){tab, position ->
            tab.text = viewPagerAdapter.getTitle(position)
        }.attach()

        btnSearch.setOnClickListener {
            viewPagerAdapter.onSearch(etSearch.text.toString())
        }
    }
}