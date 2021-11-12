package com.example.social_network_android.ui.home.login

import com.example.social_network_android.ui.base.IBasePresenter

interface ILoginPresenter {
    fun validate(email: String, password: String): Boolean
}
