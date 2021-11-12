package com.example.social_network_android.ui.base

import com.example.social_network_android.data.api.AppApi
import okhttp3.internal.http1.Http1ExchangeCodec
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.http.HTTP
import java.net.HttpURLConnection

abstract class BasePresenter: IBasePresenter {
    protected var baseView: IBaseView? = null

    override fun onAttach(baseView: IBaseView) {
        this.baseView = baseView
    }
    override fun onDetach() {
        baseView = null
    }

   protected fun handleApiError(statusCode: Int) {
        when(statusCode) {
            HttpURLConnection.HTTP_UNAUTHORIZED -> baseView?.onLogout()
            HttpURLConnection.HTTP_BAD_REQUEST -> baseView?.onBadRequestError()
            HttpURLConnection.HTTP_INTERNAL_ERROR -> baseView?.onServerError()
            HttpURLConnection.HTTP_NOT_FOUND -> baseView?.onNotFoundError()
            HttpURLConnection.HTTP_CONFLICT -> baseView?.onConflictError()
        }
    }
}