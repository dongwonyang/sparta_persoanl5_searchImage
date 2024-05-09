package com.example.sparta_personal5_search_re1.network.data.repository

import com.example.sparta_personal5_search_re1.network.data.model.SearchEntity

interface SearchRepository {
    suspend fun getSearchImage(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ): SearchEntity
}