package com.deviget.omarhezi.toppostsreddit.models.responses

import com.deviget.omarhezi.toppostsreddit.models.core.TopPostsPage
import com.google.gson.annotations.SerializedName

data class TopPostsResponse(
    @SerializedName("data")
    val topPostsData: TopPostsDataResponse = TopPostsDataResponse()
) {
    fun toTopPostsPage() = TopPostsPage(
        after = topPostsData.after,
        posts = topPostsData.children.map { it.toPost() }
    )
}