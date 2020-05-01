package com.deviget.omarhezi.toppostsreddit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.deviget.omarhezi.toppostsreddit.R
import com.deviget.omarhezi.toppostsreddit.api.ResponseResult
import com.deviget.omarhezi.toppostsreddit.models.viewdata.PostViewData
import com.deviget.omarhezi.toppostsreddit.usecase.GetTopPosts

class TopPostsViewModel(getTopPosts: GetTopPosts) : ViewModel() {
    private var _after: String? = null

    val topPosts = getTopPosts().map { result ->

        when (result) {
            ResponseResult.Loading -> TopPostsViewState.Loading
            is ResponseResult.Error -> {
                if (!result.message.isNullOrEmpty()) TopPostsViewState.Failed(message = result.message)
                else TopPostsViewState.Failed(messageResource = getMessageResource(result.errorType))
            }
            is ResponseResult.Success -> {
                _after = result.data.after
                TopPostsViewState.Loaded(result.data.posts.orEmpty().map {
                    it.toPostViewData()
                })
            }
        }
    }

    private fun getMessageResource(errorType: ResponseResult.ErrorType?) =
        when (errorType) {
            ResponseResult.ErrorType.NETWORK -> R.string.network_error_message
            ResponseResult.ErrorType.GENERIC -> R.string.generic_error_message
            else -> R.string.generic_error_message
        }

    sealed class TopPostsViewState {
        object Loading : TopPostsViewState()
        data class Failed(val message: String? = null, val messageResource: Int? = null) :
            TopPostsViewState()
        data class Loaded(val posts: List<PostViewData>) : TopPostsViewState()
    }
}