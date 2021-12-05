package com.example.social_network_android.data.api

import com.example.social_network_android.data.api.protected.ProtectedApi
import com.example.social_network_android.data.api.public.PublicApi
import com.example.social_network_android.utils.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppApi {
    interface ApiInterface {
    }
    companion object {
        const val BASE_URL = "http://10.0.2.2:8080/"
        fun create(apiType: Constants.ApiType, token: String = "") : ApiInterface {
            val client = when(apiType) {
                Constants.ApiType.ProtectedApi -> OkHttpClient.Builder()
                    .addInterceptor (Interceptor { chain ->
                    val newRequest: Request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                    chain.proceed(newRequest)
                }).build()
                Constants.ApiType.PublicApi -> OkHttpClient.Builder().build()
            }
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            return when(apiType) {
                Constants.ApiType.PublicApi -> retrofit.create(PublicApi::class.java)
                Constants.ApiType.ProtectedApi -> retrofit.create(ProtectedApi::class.java)
            }
        }
    }
}