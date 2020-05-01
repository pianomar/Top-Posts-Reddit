package com.deviget.omarhezi.toppostsreddit.models.responses

import com.google.gson.annotations.SerializedName

data class TopPostChildrenData(
    @SerializedName("created_utc")
    val createdTimeStampUTC: Long = 0,
    val imageResponses: List<PostImageResponse> = listOf(),
    @SerializedName("num_comments")
    val commentsNumber: Int = 0,
    @SerializedName("thumbnail")
    val thumbnailUrl: String = "",
    val title: String = "",
    val author: String = ""
)