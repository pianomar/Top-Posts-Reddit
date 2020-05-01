package com.deviget.omarhezi.toppostsreddit.models.core

import com.deviget.omarhezi.toppostsreddit.models.viewdata.PostViewData
import com.deviget.omarhezi.toppostsreddit.extensions.timeStampToRelativeTimeString

data class Post(
    val id: String? = null,
    val createdTimeStampUTC: Long = 0,
    val commentsNumber: Int = 0,
    val thumbnailUrl: String = "",
    val title: String = "",
    val imageHeight: Int = 0,
    val imageUrl: String = "",
    val imageWidth: Int = 0,
    val author: String = ""
) {
    fun toPostViewData() =
        PostViewData(
            id = id,
            createdDateString = createdTimeStampUTC.timeStampToRelativeTimeString(),
            commentsNumber = commentsNumber,
            title = title,
            thumbnailUrl = thumbnailUrl,
            imageWidth = imageWidth,
            imageHeight = imageHeight,
            imageUrl = imageUrl,
            author = author
        )
}