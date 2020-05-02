package com.deviget.omarhezi.toppostsreddit.models.viewdata

import android.os.Parcelable
import android.view.View
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostViewData(
    val id: String? = null,
    val createdDateString: String = "",
    val commentsNumber: Int = 0,
    val thumbnailUrl: String = "",
    val title: String = "",
    val imageHeight: Int = 0,
    val imageUrl: String = "",
    val imageWidth: Int = 0,
    var seenIndicatorVisibility: Int? = View.VISIBLE,
    val author: String
) : Parcelable