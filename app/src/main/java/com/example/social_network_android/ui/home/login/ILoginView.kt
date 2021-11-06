package com.example.social_network_android.ui.home.login

import com.example.social_network_android.ui.base.IBaseView

interface ILoginView: IBaseView {
    fun onLoginSuccess()
    fun onLoginError()
}