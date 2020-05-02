package com.deviget.omarhezi.toppostsreddit.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deviget.omarhezi.toppostsreddit.R
import com.deviget.omarhezi.toppostsreddit.models.viewdata.PostViewData
import com.deviget.omarhezi.toppostsreddit.ui.viewholders.PostViewHolder

class PostsAdapter(private val itemSelectionListener: OnClickListener? = null) : RecyclerView.Adapter<PostViewHolder>() {
    private val _items: MutableList<PostViewData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.post_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        holder.bind(_items[position], itemSelectionListener)

    override fun getItemCount(): Int = _items.size

    fun setItems(items: List<PostViewData>) {
        _items.clear()
        _items.addAll(items)
        notifyDataSetChanged()
    }

    fun loadMoreItems(items: List<PostViewData>) {
        _items.addAll(items)
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun dismissPost(postViewData: PostViewData)

        fun selectPost(postViewData: PostViewData)
    }
}