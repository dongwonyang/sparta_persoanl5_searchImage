package com.example.sparta_personal5_search_re1.network.data.model

import java.util.Date

data class SearchEntity (
    val meta: MetaEntity?,
    val documents: List<SearchImageDocumentEntity>?
)

data class MetaEntity(
    val totalCount: Int?,
    val pageableCount: Int?,
    val isEnd: Boolean?
)

data class SearchImageDocumentEntity(
    val collection: String?,
    val thumbnailUrl: String?,
    val imageUrl: String?,
    val width: Int?,
    val height: Int?,
    val displaySiteName: String?,
    val docUrl: String?,
    val dateTime: Date?
)