package com.example.social_network_android.ui.home.profile.mainProfile

import com.example.social_network_android.utils.Constants

interface IProfilePresenter {
    fun getProfile()
    fun onMediaItemClick(position: Int, type: Constants.MediaFileType)
    fun onUserInfoClick()
}