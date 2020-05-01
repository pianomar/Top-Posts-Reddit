package com.deviget.omarhezi.toppostsreddit.models.viewdata

data class PostViewData(
    val createdDateString: String = "",
    val commentsNumber: Int = 0,
    val thumbnailUrl: String = "",
    val title: String = "",
    val imageHeight: Int = 0,
    val imageUrl: String = "",
    val imageWidth: Int = 0
)