package com.example.social_network_android.ui.base

import android.content.Context
import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import okhttp3.internal.http1.Http1ExchangeCodec
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.http.HTTP
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

abstract class BasePresenter: IBasePresenter {
    protected var baseView: IBaseView? = null
    protected var preferencesHelper: PreferencesHelper? = null
    override fun onAttach(baseView: IBaseView, preferencesHelper: PreferencesHelper?) {
        this.baseView = baseView
        this.preferencesHelper = preferencesHelper
    }

    override fun onDetach() {
        baseView = null
        preferencesHelper = null
    }
   protected fun handleApiError(statusCode: Int = -1, errorBody: String = "", t: Throwable? = null) {
        when(t) {
            is SocketTimeoutException -> baseView?.onError()
            is HttpException -> handleHttpError(t.code(), t.response()!!.errorBody()!!.string())
            else -> handleHttpError(statusCode, errorBody)
        }
    }
    private fun handleHttpError(statusCode: Int, errorBody: String) {
        when(statusCode) {
            HttpURLConnection.HTTP_UNAUTHORIZED -> {
                preferencesHelper?.setLoginMode(PreferencesHelper.LoginMode.LOGGED_OUT)
                baseView?.onUnAuthorizeError()
            }
            HttpURLConnection.HTTP_BAD_REQUEST -> baseView?.onBadRequestError()
            HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                val jsonObject: JSONObject = JSONObject(errorBody)
                val code = jsonObject.opt("status")
                code?.let {
                    when(it) {
                        HttpURLConnection.HTTP_BAD_REQUEST -> baseView?.onBadRequestError()
                        else -> baseView?.onError()
                    }
                }
            }
            HttpURLConnection.HTTP_NOT_FOUND -> baseView?.onNotFoundError()
            HttpURLConnection.HTTP_CONFLICT -> baseView?.onConflictError()
        }
    }
}