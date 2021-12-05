package com.example.social_network_android.ui.home.profile.userInfo


import com.example.social_network_android.ui.base.IBasePresenter
import com.example.social_network_android.ui.base.IBaseView
import com.example.social_network_android.utils.Constants

interface IUserInfoPresenter: IBasePresenter {
    fun saveInformation(birthday: String, sex: Constants.Sex,  )
    fun loadUserInfo()
}