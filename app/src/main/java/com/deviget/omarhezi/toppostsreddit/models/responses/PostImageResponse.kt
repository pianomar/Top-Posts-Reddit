package com.deviget.omarhezi.toppostsreddit.models.responses

import com.google.gson.annotations.SerializedName

data class PostImageResponse(
    @SerializedName("source")
    val postImageSourceResponse: PostImageSourceResponse = PostImageSourceResponse()
)