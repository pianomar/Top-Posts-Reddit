package com.deviget.omarhezi.toppostsreddit.usecase

import com.deviget.omarhezi.toppostsreddit.models.responses.RequestParams
import com.deviget.omarhezi.toppostsreddit.repositories.RedditRepository

class GetTopPosts(private val repository: RedditRepository) {
    operator fun invoke(request: RequestParams? = RequestParams()) =
        repository.getNextTopPostsPage(request)
}