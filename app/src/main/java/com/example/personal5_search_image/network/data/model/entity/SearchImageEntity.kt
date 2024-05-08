package com.example.personal5_search_image.network.data.model.entity

import java.util.Date

data class SearchImageEntity(
    val meta: MetaEntity,
    val documents: List<DocumentsImageEntity>
)

data class MetaEntity(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)

data class DocumentsImageEntity(
    val collection: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val displaySiteName: String,
    val docUrl: String,
    val dateTime: Date
)