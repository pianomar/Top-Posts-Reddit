package com.deviget.omarhezi.toppostsreddit.api

sealed class Result<out R> {
    data class Loading<out T>(val cachedData: T? = null): Result<T>()
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
}