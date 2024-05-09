package com.example.sparta_personal5_search_re1.network.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchResponse (
    @SerializedName("meta") val meta: MetaResponse?,
    @SerializedName("documents") val documents: List<SearchImageDocumentResponse>?
)

data class MetaResponse(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("pageable_count") val pageableCount: Int?,
    @SerializedName("is_end") val isEnd: Boolean?
)

data class SearchImageDocumentResponse(
    @SerializedName("collection") val collection: String?,
    @SerializedName("thumbnail_url") val thumbnailUrl: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("display_sitename") val displaySiteName: String?,
    @SerializedName("doc_url") val docUrl: String?,
    @SerializedName("datetime") val dateTime: Date?
)
