package com.example.personal5_search_image.network.data.repository

import com.example.personal5_search_image.network.data.model.entity.SearchImageEntity
import com.example.personal5_search_image.network.data.model.mapper.toEntity
import com.example.personal5_search_image.network.data.remote.SearchRemoteDataResource

class SearchRepositoryImpl(
    private val remoteDataResource: SearchRemoteDataResource
)
    : SearchRepository {
    override suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): SearchImageEntity = remoteDataResource.getSearchImage(
        query,
        sort,
        page,
        size
    ).toEntity()
}