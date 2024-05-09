package com.example.sparta_personal5_search_re1.network.data.model

import java.util.Date

fun SearchResponse.toEntity() = SearchEntity(
    meta = meta?.toEntity(),
    documents = documents?.map { it.toEntity() }
)

fun MetaResponse.toEntity() = MetaEntity(
    totalCount = totalCount,
    pageableCount = pageableCount,
    isEnd = isEnd
)

fun SearchImageDocumentResponse.toEntity() = SearchImageDocumentEntity(
    collection = collection,
    thumbnailUrl = thumbnailUrl,
    imageUrl = imageUrl,
    width = width,
    height = height,
    displaySiteName = displaySiteName,
    docUrl = docUrl,
    dateTime = dateTime
)

