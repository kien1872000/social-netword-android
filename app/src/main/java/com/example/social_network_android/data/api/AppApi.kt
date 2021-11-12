package com.example.social_network_android.data.api

import com.example.social_network_android.data.api.auth.AuthApi
import com.example.social_network_android.data.api.auth.model.SignupReq
import com.example.social_network_android.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class AppApi {
    interface ApiInterface {
    }
    companion object {
        const val BASE_URL = "http://10.0.2.2:8080/"
        fun create(apiType: Constants.ApiType) : ApiInterface {
            val client = OkHttpClient.Builder()
                .build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            return when(apiType) {
                Constants.ApiType.AuthApi -> retrofit.create(AuthApi::class.java)
            }
        }
    }
}