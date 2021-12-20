package com.example.social_network_android.ui.home.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.social_network_android.R
import com.example.social_network_android.data.api.protected.model.File
import com.example.social_network_android.data.api.protected.model.MediaFileRes
import com.example.social_network_android.data.api.protected.model.PostRes
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.home.baseHome.BaseHomeFragment
import kotlinx.android.synthetic.main.fragment_post.*


class PostFragment : IPostView, BaseHomeFragment() {
    var postPresenter: PostPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postPresenter = PostPresenter().also {
            it.onAttach(this, PreferencesHelper(requireContext()))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postPresenter!!.loadPosts()
    }
    override fun showPosts(postList: List<PostRes>) {
        var files: List<List<File>> = postList.map { it.files!! }
        files = files.filter { it.isNotEmpty() }
        Log.d("h111", files.size.toString())
        post_list.adapter = PostAdapter(requireContext(), postList)
        post_list.layoutManager = LinearLayoutManager(requireContext())
    }


}