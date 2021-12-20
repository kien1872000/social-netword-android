package com.example.social_network_android.ui.home.post

import com.example.social_network_android.data.api.protected.model.PostRes
import com.example.social_network_android.ui.home.baseHome.IBaseHomeView

interface IPostView: IBaseHomeView {
    fun showPosts(postList: List<PostRes>)
}