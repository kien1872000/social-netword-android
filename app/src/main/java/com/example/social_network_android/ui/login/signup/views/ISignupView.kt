package com.example.social_network_android.ui.login.signup.views

import com.example.social_network_android.ui.base.IBaseView

interface ISignupView: IBaseView {
}
interface IActivationView: IBaseView{
    fun onActivationSuccess()
    fun onResendSuccess()
    fun showNewsFeed()
}