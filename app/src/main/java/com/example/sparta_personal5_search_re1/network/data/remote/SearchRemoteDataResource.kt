package com.example.sparta_personal5_search_re1.network.data.remote

import com.example.sparta_personal5_search_re1.network.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteDataResource {
    @GET("v2/search/image")
    suspend fun getImage(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80
    ): SearchResponse
}