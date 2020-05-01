package com.deviget.omarhez.toppostsreddit.models.responses

import com.google.gson.annotations.SerializedName

data class TopPostsChildrenResponse(
    @SerializedName("data")
    val topPostChildrenData: TopPostChildrenData = TopPostChildrenData()
)