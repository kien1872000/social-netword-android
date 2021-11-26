package com.example.social_network_android.ui.login.signup

interface ISignupPresenter {
    fun doSignup(displayName: String, birthday: String, sex: Int, email: String, password: String)
    fun doActivate(email: String, activationCode: String)
    fun sendActivationCode(email: String)
}