package com.example.personal5_search_image.network.data.remote

import com.example.personal5_search_image.network.data.model.reponse.SearchImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteDataResource {
    @GET("v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): SearchImageResponse
}