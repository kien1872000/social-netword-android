package com.example.social_network_android.data.api.protected

import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.protected.model.FollowingRes
import com.example.social_network_android.data.api.protected.model.UserProfileRes
import io.reactivex.Observable
import retrofit2.http.GET

interface ProtectedApi: AppApi.ApiInterface {
    @GET("user/profile")
    fun getUserProfile(): Observable<UserProfileRes>
    @GET("following/get/followings")
    fun getFollowings(): Observable<List<FollowingRes>>
}