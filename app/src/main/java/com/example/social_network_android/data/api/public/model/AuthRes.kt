package com.example.social_network_android.data.api.public.model

data class LoginRes(
    val accessToken: String,
    val displayName: String,
    val avatar: String,
    val sex: String
)