package com.example.social_network_android.ui.base

interface IBasePresenter {
    fun onAttach(baseView: IBaseView)
    fun onDetach()
}