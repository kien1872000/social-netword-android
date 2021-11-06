package com.example.social_network_android.ui.home.login

import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.ui.base.IBaseView

class LoginPresenter (private var loginView: ILoginView?): BasePresenter(),ILoginPresenter {
    override fun validate(email: String, password: String): Boolean {
        return (email=="kien@gmail.com"&&password=="123456")
    }
    fun doLogin(email: String, password: String) {
        if(validate(email, password)) {
            loginView!!.onLoginSuccess()
        }
        else {
            loginView!!.onLoginError()
        }
    }


}