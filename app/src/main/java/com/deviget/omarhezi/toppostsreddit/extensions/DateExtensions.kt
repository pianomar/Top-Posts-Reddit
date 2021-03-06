package com.deviget.omarhezi.toppostsreddit.extensions

import java.util.*
import java.util.concurrent.TimeUnit

fun Long.timeStampToRelativeTimeString(): String {
    val diff = Date().time - this
    return "${TimeUnit.MILLISECONDS.toHours(diff)} hours ago"
}