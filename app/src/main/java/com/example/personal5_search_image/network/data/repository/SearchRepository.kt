package com.example.personal5_search_image.network.data.repository

import com.example.personal5_search_image.network.data.model.entity.SearchImageEntity
import retrofit2.http.Query

interface SearchRepository {
    suspend fun getSearchImage(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ): SearchImageEntity
}