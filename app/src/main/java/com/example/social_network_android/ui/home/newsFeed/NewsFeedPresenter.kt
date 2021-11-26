package com.example.social_network_android.ui.home.newsFeed

import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.protected.ProtectedApi
import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.utils.Constants

class NewsFeedPresenter: BasePresenter() {
    private val protectedApi: ProtectedApi = AppApi.create(Constants.ApiType.ProtectedApi) as ProtectedApi
}