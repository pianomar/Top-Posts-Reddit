package com.deviget.omarhezi.toppostsreddit.models.responses

import com.deviget.omarhezi.toppostsreddit.models.core.Post
import com.google.gson.annotations.SerializedName

data class TopPostsChildrenResponse(
    @SerializedName("data")
    val topPostChildrenData: TopPostChildrenData = TopPostChildrenData()
) {
    fun toPost(): Post {
        val image = topPostChildrenData.preview.imageResponses.firstOrNull()?.postImageSourceResponse
        return Post(
            createdTimeStampUTC = topPostChildrenData.createdTimeStampUTC * 1000,
            commentsNumber = topPostChildrenData.commentsNumber,
            thumbnailUrl = topPostChildrenData.thumbnailUrl,
            title = topPostChildrenData.title,
            imageUrl = image?.url.orEmpty(),
            imageHeight = image?.height ?: 0,
            imageWidth = image?.width ?: 0,
            author = topPostChildrenData.author
        )
    }
}