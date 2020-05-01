package com.deviget.omarhez.toppostsreddit.models.responses

data class RequestParams(
    val after: String? = null,
    val count: Int = DEFAULT_COUNT
) {
    companion object {
        const val DEFAULT_COUNT = 20
    }
}