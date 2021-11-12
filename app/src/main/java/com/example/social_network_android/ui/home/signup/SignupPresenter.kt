package com.example.social_network_android.ui.home.signup

import android.util.Log
import android.widget.Toast
import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.auth.AuthApi
import com.example.social_network_android.data.api.auth.model.SignupReq
import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.ui.base.IBaseView
import com.example.social_network_android.ui.home.signup.views.ISignupView
import com.example.social_network_android.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class SignupPresenter: BasePresenter(), ISignupPresenter {
    private var authApi: AuthApi = AppApi.create(Constants.ApiType.AuthApi) as AuthApi
    override fun doSignup(
        displayName: String,
        birthday: String,
        sex: Int,
        email: String,
        password: String
    ) {
        val signupReq = SignupReq(displayName, birthday, sex, email, password)
        baseView?.showLoading()
        authApi.createAccount(signupReq).enqueue(object : Callback, retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                baseView?.hideLoading()
                if(response.isSuccessful) {
                    (baseView as ISignupView)?.onSignupSuccess()
                }
                else {
                    handleApiError(response.code())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                throw t
            }

        } )
    }
}