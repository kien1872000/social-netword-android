package com.example.social_network_android.data.api.public

import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.public.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface PublicApi: AppApi.ApiInterface {
    // @Headers("CONNECT_TIMEOUT:30000", "READ_TIMEOUT:30000", "WRITE_TIMEOUT:30000")
//    @PUT('user/auth/reset-password')
//    fun resetPassword(@Body() resetPasswordReq: ResetPasswordReq): Call<ResponseBody>
//    @PUT('user/auth/send/resetlink')
//    fun sendResetLink(@Body() sendResetLinkReq: SendResetLinkReq): Call<ResponseBody>
    @POST("user/auth/login")
    fun login(@Body() loginInput: LoginReq): Call<LoginRes>
    @PUT("user/auth/send/activationCode")
    fun sendActivationCode(@Body() sendActivationReq: SendActivationCodeReq): Call<ResponseBody>
    @PUT("user/auth/activate-account")
    fun activateAccount(@Body() activate: ActivationReq): Call<ResponseBody>
    @POST("user/auth/signup")
    fun createAccount(@Body signupReq: SignupReq): Call<ResponseBody>
}