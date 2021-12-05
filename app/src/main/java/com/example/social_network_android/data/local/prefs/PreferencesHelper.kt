package com.example.social_network_android.data.local.prefs

import android.content.Context
import androidx.core.content.edit

class PreferencesHelper(context: Context): IPreferencesHelper{
    enum class LoginMode(val value: Int) {
        LOGGED_OUT(0),
        LOGGED_IN(1)
    }
    private val sharePrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    override fun getLoginMode(): Int = sharePrefs.getInt(PREFS_KEY_LOGIN_MODE, LoginMode.LOGGED_OUT.value)

    override fun setLoginMode(mode: LoginMode) {
        sharePrefs.edit { putInt(PREFS_KEY_LOGIN_MODE, mode.value) }
    }

    override fun setAccessToken(token: String) {
        sharePrefs.edit{putString(PREFS_KEY_ACCESS_TOKEN, token)}
    }

    override fun getAccessToken(): String = sharePrefs.getString(PREFS_KEY_ACCESS_TOKEN, "")!!
    override fun setUserName(userName: String) {
        sharePrefs.edit{putString(PREFS_KEY_USER_NAME, userName)}
    }

    override fun getUserName(): String = sharePrefs.getString(PREFS_KEY_USER_NAME, "")!!

    companion object {
        private const val PREFS_NAME = "user_data"
        private const val PREFS_KEY_LOGIN_MODE = "pref_key_login_mode"
        private const val PREFS_KEY_ACCESS_TOKEN = "pref_key_access_token"
        private const val PREFS_KEY_USER_NAME = "pref_key_user_name"
    }

}