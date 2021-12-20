package com.example.social_network_android.ui.login.signup

import android.util.Log
import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.public.PublicApi
import com.example.social_network_android.data.api.public.model.*
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.ui.login.signup.views.IActivationView
import com.example.social_network_android.ui.login.signup.views.ISignupView
import com.example.social_network_android.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.security.auth.callback.Callback

class SignupPresenter : BasePresenter(), ISignupPresenter {
    private var publicApi: PublicApi = AppApi.create(Constants.ApiType.PublicApi) as PublicApi
    override fun doSignup(
        displayName: String,
        birthday: String,
        sex: Int,
        email: String,
        password: String,
    ) {
        val signupReq = SignupReq(displayName, birthday, sex, email, password)
        baseView?.showLoading()
        publicApi.createAccount(signupReq)
            .enqueue(object : Callback, retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>,
                ) {
                    Log.d("H111", response.errorBody().toString())
                    baseView?.hideLoading()
                    if (response.isSuccessful) {
                        (baseView as ISignupView)?.onSuccess()
                    } else {
                        handleApiError(response.code(), response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    handleApiError(t = t)
                }

            })
    }
    override fun doActivate(email: String, activationCode: String, password: String) {
        val activationReq = ActivationReq(email, activationCode)
        publicApi.activateAccount(activationReq)
            .enqueue(object : Callback, retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>,
                ) {
                    if (response.isSuccessful) {
                        (baseView as IActivationView).onActivationSuccess()
                    } else {
                        handleApiError(response.code(), response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    handleApiError(t = t)
                }
            })
    }

    override fun sendActivationCode(email: String) {
        val sendActivationReq = SendActivationCodeReq(email)
        publicApi.sendActivationCode(sendActivationReq)
            .enqueue(object : Callback, retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>,
                ) {
                    if (response.isSuccessful) {
                        (baseView as IActivationView).onResendSuccess()
                    } else {
                        handleApiError(response.code(), response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    handleApiError(t = t)
                }
            })
    }

    override fun doLogin(email: String, password: String) {
        val loginReq = LoginReq(email, password)
        baseView?.showLoading()
        publicApi.login(loginReq).enqueue(object: retrofit2.Callback<LoginRes>{
            override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {
                baseView?.hideLoading()
                if(response.isSuccessful) {
                    preferencesHelper?.apply {
                        setAccessToken(response.body()!!.accessToken)
                        setUserName(response.body()!!.displayName)
                        setLoginMode(PreferencesHelper.LoginMode.LOGGED_IN)
                    }
                    (baseView as IActivationView).showNewsFeed()
                }
                else{
                    handleApiError(response.code(), response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                baseView?.hideLoading()
                handleApiError(t=t)
            }

        })
    }
}