package com.example.social_network_android.data.api.auth

import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.auth.model.ActivationReq
import com.example.social_network_android.data.api.auth.model.SignupReq
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthApi: AppApi.ApiInterface {
    // @Headers("CONNECT_TIMEOUT:30000", "READ_TIMEOUT:30000", "WRITE_TIMEOUT:30000")
//    @PUT('user/auth/reset-password')
//    fun resetPassword(@Body() resetPasswordReq: ResetPasswordReq): Call<ResponseBody>
//    @PUT('user/auth/send/resetlink')
//    fun sendResetLink(@Body() sendResetLinkReq: SendResetLinkReq): Call<ResponseBody>
//    @POST("user/auth/signup")
//    fun login(@Body() loginInput: LoginReq): Call<LoginRes>
//    @PUT("user/auth/send/activationCode")
//    fun sendActivationCode(@Body() sendActivationReq: SendActivationReq): Call<ResponseBody>
    @PUT("user/auth/activate-account")
    fun activateAccount(@Body() activate: ActivationReq): Call<ResponseBody>
    @POST("user/auth/signup")
    fun createAccount(@Body signupReq: SignupReq): Call<ResponseBody>
}