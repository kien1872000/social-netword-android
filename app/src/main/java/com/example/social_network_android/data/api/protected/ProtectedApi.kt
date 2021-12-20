package com.example.social_network_android.data.api.protected

import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.protected.model.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

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

    @GET("address/provinces")
    fun getProvinces(): Call<List<Address>>
    @GET("address/districts/{provinceId}")
    fun getDistricts(@Path("provinceId") provinceId: Int): Call<List<Address>>
    @GET("address/wards/{districtId}")
    fun getWards(@Path("districtId") districtId: Int): Call<List<Address>>

    @PUT("user/update-info")
    fun updateUserInfo(@Body() userInfoReq: UserInfoReq): Call<UserProfileRes>

    @GET("post/posts?postLimit=newsfeed")
    fun getPosts(@Query("postLimit") postLimit: String): Call<List<PostRes>>
}