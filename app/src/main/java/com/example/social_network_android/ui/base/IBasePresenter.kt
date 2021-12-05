package com.example.social_network_android.ui.base

import android.content.Context
import android.content.SharedPreferences
import com.example.social_network_android.data.local.prefs.PreferencesHelper

interface IBasePresenter {
    fun onAttach(baseView: IBaseView, preferencesHelper: PreferencesHelper?)
    fun onDetach()
}