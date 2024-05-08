package com.example.personal5_search_image.network.data.model.reponse

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchImageResponse(
    @SerializedName("meta") val meta: MetaResponse,
    @SerializedName("documents") val documents: List<DocumentsImageResponse>
)


data class MetaResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)

data class DocumentsImageResponse(
    @SerializedName("collection") val collection: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("display_sitename") val displaySiteName: String,
    @SerializedName("doc_url") val docUrl: String,
    @SerializedName("datetime") val dateTime: Date
)