package com.example.social_network_android.data.local.prefs

interface IPreferencesHelper {
    fun getLoginMode(): Int
    fun setLoginMode(mode: PreferencesHelper.LoginMode)
    fun setAccessToken(token: String)
    fun getAccessToken(): String
    fun setUserName(userName: String)
    fun getUserName(): String
    fun setUserId(userId: String)
    fun getUserId(): String
}