package com.example.sparta_personal5_search_re1.network.data.repository

import com.example.sparta_personal5_search_re1.network.data.model.SearchEntity
import com.example.sparta_personal5_search_re1.network.data.model.SearchResponse
import com.example.sparta_personal5_search_re1.network.data.model.toEntity
import com.example.sparta_personal5_search_re1.network.data.remote.SearchRemoteDataResource

class SearchRepositoryImpl(
    private val searchRemoteDataResource: SearchRemoteDataResource
): SearchRepository {
    override suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): SearchEntity = searchRemoteDataResource.getImage(
        query,
        sort,
        page,
        size
    ).toEntity()
}