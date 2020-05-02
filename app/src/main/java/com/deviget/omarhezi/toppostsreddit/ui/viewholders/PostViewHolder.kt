package com.deviget.omarhezi.toppostsreddit.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deviget.omarhezi.toppostsreddit.R
import com.deviget.omarhezi.toppostsreddit.extensions.hide
import com.deviget.omarhezi.toppostsreddit.models.viewdata.PostViewData
import com.deviget.omarhezi.toppostsreddit.ui.adapters.PostsAdapter
import kotlinx.android.synthetic.main.post_item.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        viewData: PostViewData,
        itemSelectionListener: PostsAdapter.OnClickListener?
    ) {
        itemView.authorTextView.text = viewData.author
        itemView.titleTextView.text = viewData.title
        itemView.seenIndicatorImageView.visibility =
            viewData.seenIndicatorVisibility ?: View.VISIBLE
        Glide.with(itemView).load(viewData.thumbnailUrl).into(itemView.thumbnailImageView)
        itemView.createdDateTextView.text = viewData.createdDateString
        itemView.commentsNumberTextView.text =
            itemView.context.getString(R.string.comments, viewData.commentsNumber)

        itemView.setOnClickListener {
            viewData.seenIndicatorVisibility = View.GONE
            itemSelectionListener?.selectPost(viewData)
        }

        itemView.dismissPostTextView.setOnClickListener {
            itemSelectionListener?.dismissPost(viewData)
        }
    }
}