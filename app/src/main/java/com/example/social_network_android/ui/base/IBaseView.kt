package com.example.social_network_android.ui.base

interface IBaseView {
    fun showMessage(msg: String)
    fun onError()
    fun onSuccess()
    fun showLoading()
    fun hideLoading()
    fun onLogout()
    fun onBadRequestError()
    fun onNotFoundError()
    fun onConflictError()
}