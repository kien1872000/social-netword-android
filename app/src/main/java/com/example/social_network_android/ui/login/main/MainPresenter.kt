package com.example.social_network_android.ui.login.main

import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.base.BasePresenter

class MainPresenter: IMainPresenter, BasePresenter() {
    override fun loadView() {
        if(preferencesHelper!!.getLoginMode()==PreferencesHelper.LoginMode.LOGGED_IN.value) {
            (baseView as IMainView).showNewsFeed()
        }
        else {
            (baseView as IMainView).showLogin()
        }
    }
}