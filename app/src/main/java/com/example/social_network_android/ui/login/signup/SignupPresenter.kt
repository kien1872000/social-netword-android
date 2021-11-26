package com.example.social_network_android.ui.login.signup

import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.public.PublicApi
import com.example.social_network_android.data.api.public.model.ActivationReq
import com.example.social_network_android.data.api.public.model.SendActivationCodeReq
import com.example.social_network_android.data.api.public.model.SignupReq
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

    override fun doActivate(email: String, activationCode: String) {
        val activationReq = ActivationReq(email, activationCode)
        publicApi.activateAccount(activationReq)
            .enqueue(object : Callback, retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>,
                ) {
                    if (response.isSuccessful) {
                        (baseView as IActivationView)?.onSuccess()
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
                        baseView?.onSuccess()
                    } else {
                        handleApiError(response.code(), response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    handleApiError(t = t)
                }
            })
    }
}