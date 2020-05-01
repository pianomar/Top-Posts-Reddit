package com.deviget.omarhezi.toppostsreddit.api

import com.deviget.omarhezi.toppostsreddit.models.responses.TopPostsResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RedditApiService {
    @GET("top.json")
    suspend fun getTopPosts(
        @QueryMap options: MutableMap<String, String>? = null
    ): TopPostsResponse
}