package com.deviget.omarhezi.toppostsreddit.api

sealed class ResponseResult<out R> {
    object Loading : ResponseResult<Nothing>()
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Error(val errorType: ErrorType? = ErrorType.GENERIC, val message: String? = null) : ResponseResult<Nothing>()

    enum class ErrorType {
        NETWORK, GENERIC
    }
}