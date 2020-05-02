package com.deviget.omarhezi.toppostsreddit.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.deviget.omarhezi.toppostsreddit.R
import com.deviget.omarhezi.toppostsreddit.api.ResponseResult
import com.deviget.omarhezi.toppostsreddit.models.responses.RequestParams
import com.deviget.omarhezi.toppostsreddit.models.viewdata.PostViewData
import com.deviget.omarhezi.toppostsreddit.usecase.GetTopPosts

class TopPostsViewModel(getTopPosts: GetTopPosts) : ViewModel() {

    private var _after: String? = null
    private val _fetchPostsTrigger = MediatorLiveData<Boolean>()

    init {
        fetchPosts()
    }

    val topPosts = _fetchPostsTrigger.switchMap {
        getTopPosts(RequestParams(after = _after)).map { result ->
            when (result) {
                is ResponseResult.Loading -> TopPostsViewState.Loading
                is ResponseResult.Error -> {
                    if (!result.message.isNullOrEmpty()) TopPostsViewState.Failed(message = result.message)
                    else TopPostsViewState.Failed(messageResource = getMessageResource(result.errorType))
                }
                is ResponseResult.Success -> {
                    val retrievedPosts = result.data.posts.orEmpty().map {
                        it.toPostViewData()
                    }

                    if (_after != null) {
                        _after = result.data.after
                        TopPostsViewState.LoadedMore(retrievedPosts)
                    } else {
                        _after = result.data.after
                        TopPostsViewState.Loaded(retrievedPosts)
                    }
                }
            }
        }
    }


    private fun getMessageResource(errorType: ResponseResult.ErrorType?) =
        when (errorType) {
            ResponseResult.ErrorType.NETWORK -> R.string.network_error_message
            ResponseResult.ErrorType.GENERIC -> R.string.generic_error_message
            else -> R.string.generic_error_message
        }

    fun fetchPosts() {
        _fetchPostsTrigger.value = true
    }

    sealed class TopPostsViewState {
        object Loading : TopPostsViewState()
        data class Failed(
            val message: String? = null,
            val messageResource: Int = R.string.generic_error_message
        ) :
            TopPostsViewState()

        data class Loaded(val posts: List<PostViewData>) : TopPostsViewState()
        data class LoadedMore(val posts: List<PostViewData>) : TopPostsViewState()
    }
}