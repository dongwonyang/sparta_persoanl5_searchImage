package com.example.personal5_search_image.network.data.model.mapper

import com.example.personal5_search_image.network.data.model.entity.DocumentsImageEntity
import com.example.personal5_search_image.network.data.model.entity.MetaEntity
import com.example.personal5_search_image.network.data.model.entity.SearchImageEntity
import com.example.personal5_search_image.network.data.model.reponse.DocumentsImageResponse
import com.example.personal5_search_image.network.data.model.reponse.MetaResponse
import com.example.personal5_search_image.network.data.model.reponse.SearchImageResponse
import java.util.Date

fun SearchImageResponse.toEntity() =
    SearchImageEntity(
        meta = meta.toEntity(),
        documents = documents.map { it.toEntity() }
    )

fun MetaResponse.toEntity() =
    MetaEntity(
        totalCount = totalCount,
        pageableCount = pageableCount,
        isEnd = isEnd
    )

fun DocumentsImageResponse.toEntity() =
    DocumentsImageEntity(
        collection = collection,
        thumbnailUrl = thumbnailUrl,
        imageUrl = imageUrl,
        width = width,
        height = height,
        displaySiteName = displaySiteName,
        docUrl = docUrl,
        dateTime = dateTime
    )

