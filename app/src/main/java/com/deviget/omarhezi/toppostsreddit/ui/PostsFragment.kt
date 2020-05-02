package com.deviget.omarhezi.toppostsreddit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.deviget.omarhezi.toppostsreddit.R
import com.deviget.omarhezi.toppostsreddit.extensions.hide
import com.deviget.omarhezi.toppostsreddit.extensions.show
import com.deviget.omarhezi.toppostsreddit.extensions.snackBar
import com.deviget.omarhezi.toppostsreddit.models.viewdata.PostViewData
import com.deviget.omarhezi.toppostsreddit.ui.adapters.PostsAdapter
import com.deviget.omarhezi.toppostsreddit.viewmodel.TopPostsViewModel
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.android.viewmodel.ext.android.viewModel

class PostsFragment : Fragment() {

    private val viewModel by viewModel<TopPostsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_posts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PostsAdapter()

        val itemSelectionListener = object : PostsAdapter.OnClickListener {
            override fun dismissPost(postViewData: PostViewData) {
                adapter.removeItem(postViewData)
            }

            override fun selectPost(postViewData: PostViewData) {
                viewModel.onPostSelected(postViewData)
                findNavController().navigate(
                    PostsFragmentDirections.detailsFragmentAction(postViewData)
                )
            }
        }

        adapter.itemSelectionListener = itemSelectionListener

        setupRecyclerView(adapter)
        postsRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }


        viewModel.topPosts.observe(viewLifecycleOwner, Observer { viewState ->
            postsProgressBar.hide()
            when (viewState) {
                TopPostsViewModel.TopPostsViewState.Loading -> postsProgressBar.show()
                is TopPostsViewModel.TopPostsViewState.Failed ->
                    postsRoot.snackBar(viewState.message ?: getString(viewState.messageResource))
                is TopPostsViewModel.TopPostsViewState.Loaded -> {
                    adapter.setItems(viewState.posts)
                    postsRefreshLayout.isRefreshing = false
                }
                is TopPostsViewModel.TopPostsViewState.LoadedMore -> {
                    adapter.loadMoreItems(viewState.posts)
                }
            }
        })
    }

    private fun setupRecyclerView(adapter: PostsAdapter) {
        postsRecyclerView.apply {
            this.adapter = adapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) viewModel.fetchPosts()
                }
            })
        }
        postsRecyclerView.adapter = adapter
    }
}
