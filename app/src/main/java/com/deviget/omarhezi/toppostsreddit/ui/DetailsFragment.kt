package com.deviget.omarhezi.toppostsreddit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.deviget.omarhezi.toppostsreddit.R
import com.deviget.omarhezi.toppostsreddit.extensions.hide
import com.deviget.omarhezi.toppostsreddit.extensions.show
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private val navArgs: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (navArgs.postViewData == null) {
            noPostSelectedTextView.show()
            return
        }

        noPostSelectedTextView.hide()
        titleTextView.text = navArgs.postViewData?.title
        authorTextView.text = navArgs.postViewData?.author

        Glide.with(view).load(navArgs.postViewData?.imageUrl).into(postImageView)
    }

}
