package com.example.social_network_android.ui.home.signup

interface ISignupPresenter {
    fun doSignup(displayName: String, birthday: String, sex: Int, email: String, password: String)
}