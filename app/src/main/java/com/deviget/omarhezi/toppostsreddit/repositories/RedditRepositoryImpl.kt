package com.deviget.omarhezi.toppostsreddit.repositories

import android.util.Log
import androidx.lifecycle.liveData
import com.deviget.omarhezi.toppostsreddit.api.RedditApiService
import com.deviget.omarhezi.toppostsreddit.api.ResponseResult
import com.deviget.omarhezi.toppostsreddit.models.responses.RequestParams
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class RedditRepositoryImpl(private val api: RedditApiService) : RedditRepository {

    companion object {
        const val TAG = "RedditRepositoryImp"
    }

    override fun getNextTopPostsPage(requestParams: RequestParams?) = liveData {
        emit(ResponseResult.Loading)

        try {
            val queryMap = mutableMapOf(Pair("count", requestParams?.count.toString()))

            requestParams?.after?.let {
                queryMap["after"] = it
            }

            val response = api.getTopPosts(queryMap).toTopPostsPage()
            emit(ResponseResult.Success(response))
        } catch (throwable: Throwable) {
            emit(handleResponseError(throwable))
        }
    }

    private fun handleResponseError(throwable: Throwable) =
        when (throwable) {
            is IOException -> ResponseResult.Error(ResponseResult.ErrorType.NETWORK)
            is HttpException -> {
                Log.e(TAG, throwable.code().toString())
                val errorResponse = throwable.response()?.errorBody()?.toString()?.let {
                    Gson().fromJson(it, String::class.java)
                }
                ResponseResult.Error(ResponseResult.ErrorType.GENERIC, errorResponse)
            }
            else -> {
                ResponseResult.Error(ResponseResult.ErrorType.GENERIC)
            }
        }
}