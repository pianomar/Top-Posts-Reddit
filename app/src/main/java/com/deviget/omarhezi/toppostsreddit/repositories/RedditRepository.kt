package com.deviget.omarhezi.toppostsreddit.repositories

import androidx.lifecycle.LiveData
import com.deviget.omarhezi.toppostsreddit.api.ResponseResult
import com.deviget.omarhezi.toppostsreddit.models.core.TopPostsPage
import com.deviget.omarhezi.toppostsreddit.models.responses.RequestParams

interface RedditRepository {
    fun getNextTopPostsPage(requestParams: RequestParams? = null): LiveData<ResponseResult<TopPostsPage>>
}