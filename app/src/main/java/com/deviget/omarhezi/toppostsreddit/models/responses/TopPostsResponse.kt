package com.deviget.omarhezi.toppostsreddit.models.responses

import com.google.gson.annotations.SerializedName

data class TopPostsResponse(
    @SerializedName("data")
    val topPostsData: TopPostsDataResponse = TopPostsDataResponse()
)