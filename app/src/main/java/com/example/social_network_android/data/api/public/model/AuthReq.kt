package com.example.social_network_android.data.api.public.model

data class SignupReq(
    var displayName: String,
    var birthday: String,
    var sex: Int,
    var email: String,
    var password: String,
)
data class ActivationReq(
    var email: String,
    var activationCode: String
)
data class SendActivationCodeReq(var email: String)
data class LoginReq(var email: String, var password: String)
