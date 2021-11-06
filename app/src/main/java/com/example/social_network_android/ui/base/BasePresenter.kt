package com.example.social_network_android.ui.base

abstract class BasePresenter: IBasePresenter {
    private var baseView: IBaseView? = null
    override fun onAttach(baseView: IBaseView) {
        this.baseView = baseView
    }
    override fun onDetach() {
        baseView = null
    }
}