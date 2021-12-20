package com.example.social_network_android.ui.home.post

import android.util.Log
import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.protected.ProtectedApi
import com.example.social_network_android.data.api.protected.model.PostRes
import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.utils.Constants
import retrofit2.Call
import retrofit2.Response

class PostPresenter: IPostPresenter, BasePresenter() {
    override fun loadPosts() {
        val protectedApi = AppApi.create(Constants.ApiType.ProtectedApi, preferencesHelper!!.getAccessToken()) as ProtectedApi
        protectedApi.getPosts("newsfeed").enqueue(object: retrofit2.Callback<List<PostRes>>{
            override fun onResponse(call: Call<List<PostRes>>, response: Response<List<PostRes>>) {
                if(response.isSuccessful) {
                    Log.d("post", response.body().toString())
                   (baseView as IPostView).showPosts(response.body()!!)
                }
                else {
                    handleApiError(response.code(), response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<List<PostRes>>, t: Throwable) {
                handleApiError(t=t)
            }

        })
    }
}