package com.deviget.omarhezi.toppostsreddit.models.responses

data class TopPostsDataResponse(
    val after: String = "",
    val children: List<TopPostsChildrenResponse> = listOf()
)