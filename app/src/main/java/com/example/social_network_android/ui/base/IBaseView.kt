package com.example.social_network_android.ui.base

interface IBaseView {
    fun showMessage(msg: String)
    fun onError(error: String)
    fun onSuccess(msg: String)
    fun showLoading()
}