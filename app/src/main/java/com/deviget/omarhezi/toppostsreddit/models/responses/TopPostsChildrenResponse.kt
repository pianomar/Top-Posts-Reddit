package com.deviget.omarhezi.toppostsreddit.models.responses

import com.deviget.omarhezi.toppostsreddit.models.core.Post
import com.google.gson.annotations.SerializedName

data class TopPostsChildrenResponse(
    @SerializedName("data")
    val topPostChildrenData: TopPostChildrenData = TopPostChildrenData()
) {
    fun toPost(): Post {
        val image = topPostChildrenData.imageResponses.firstOrNull()?.postImageSourceResponse
        return Post(
            createdTimeStampUTC = topPostChildrenData.createdTimeStampUTC,
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