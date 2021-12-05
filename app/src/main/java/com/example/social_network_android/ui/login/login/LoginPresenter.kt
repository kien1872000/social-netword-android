package com.example.social_network_android.ui.login.login

import android.util.Log
import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.public.PublicApi
import com.example.social_network_android.data.api.public.model.LoginReq
import com.example.social_network_android.data.api.public.model.LoginRes
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.utils.Constants
import retrofit2.Call
import retrofit2.Response
import java.net.SocketTimeoutException

class LoginPresenter() : BasePresenter(), ILoginPresenter {
    private val publicApi: PublicApi = AppApi.create(Constants.ApiType.PublicApi) as PublicApi
    override fun doLogin(email: String, password: String) {
        val loginReq = LoginReq(email, password)
        baseView?.showLoading()
        publicApi.login(loginReq).enqueue(object : retrofit2.Callback<LoginRes> {
            override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {
                baseView?.hideLoading()
                if (response.isSuccessful) {
                    preferencesHelper?.apply {
                        setAccessToken(response.body()!!.accessToken)
                        setUserName(response.body()!!.displayName)
                        setLoginMode(PreferencesHelper.LoginMode.LOGGED_IN)
                    }
                    baseView?.onSuccess()
                } else {
                    handleApiError(response.code(), response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                baseView?.hideLoading()
                handleApiError(t = t)
            }

        })
    }


}