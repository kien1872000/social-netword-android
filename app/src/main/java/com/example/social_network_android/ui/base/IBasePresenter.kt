package com.example.social_network_android.ui.base

import android.content.Context

interface IBasePresenter {
    fun onAttach(baseView: IBaseView, context: Context? = null)
    fun onDetach()
}