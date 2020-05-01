package com.deviget.omarhezi.toppostsreddit.models.viewdata

import android.view.View

data class PostViewData(
    val id: String? = null,
    val createdDateString: String = "",
    val commentsNumber: Int = 0,
    val thumbnailUrl: String = "",
    val title: String = "",
    val imageHeight: Int = 0,
    val imageUrl: String = "",
    val imageWidth: Int = 0,
    val seenIndicatorVisibility: Int? = View.VISIBLE,
    val author: String
)