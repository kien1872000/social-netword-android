package com.example.social_network_android.data.api.protected

import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.protected.model.FollowingRes
import com.example.social_network_android.data.api.protected.model.MediaFileRes
import com.example.social_network_android.data.api.protected.model.UserProfileRes
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProtectedApi : AppApi.ApiInterface {
    @GET("user/profile")
    fun getUserProfile(@Query("userId") userId: String?): Observable<UserProfileRes>


    @GET("media-files/profile/media-files")
    fun getMediaFiles(
        @Query("userId") userId: String?,
        @Query("pageNumber") pageNumber: Int,
        @Query("type") type: String,
    ): Observable<List<MediaFileRes>>

    @GET("media-files/profile/media-files")
    fun getMediaFiles(
        @Query("type") type: String,
        @Query("userId") userId: String?,
        @Query("pageNumber") pageNumber: Int,
    ): Call<List<MediaFileRes>>

    @GET("following/get/followings")
    fun getFollowings(): Observable<List<FollowingRes>>
}