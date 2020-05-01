package com.deviget.omarhezi.toppostsreddit.models.core

data class TopPostsPage(
    val after: String = "",
    val posts: List<Post>? = null
)