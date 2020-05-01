package com.deviget.omarhezi.toppostsreddit.models.core

data class Post(
    val createdTimeStampUTC: Long = 0,
    val commentsNumber: Int = 0,
    val thumbnailUrl: String = "",
    val title: String = "",
    val imageHeight: Int = 0,
    val imageUrl: String = "",
    val imageWidth: Int = 0
)