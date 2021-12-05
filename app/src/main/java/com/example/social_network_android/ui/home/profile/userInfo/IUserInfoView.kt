package com.example.social_network_android.ui.home.profile.userInfo

import com.example.social_network_android.ui.base.IBaseView

interface IUserInfoView: IBaseView {
    fun showDisplayNameInput()
    fun showSexRadioButton()
    fun showAddressFragment()
    fun showDatePickerDialog()
    fun showCurrentUserActions()
    fun hideSexRadioButton()
    fun hideDisplayNameInput()
    fun enableSaveButton()
    fun disableSaveButton()
    fun hideCurrentUserActions()
    fun initProfileView(_displayName: String, _birthday: String, _sex: String, _address: String?)
}