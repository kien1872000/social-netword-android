package com.example.social_network_android.ui.home.profile.userInfo

import com.example.social_network_android.data.api.protected.model.Address
import com.example.social_network_android.data.api.protected.model.AddressRes
import com.example.social_network_android.data.api.protected.model.UserInfo
import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.utils.CommonUtils
import com.example.social_network_android.utils.Constants

class UserInfoPresenter(private val userInfo: UserInfo) : IUserInfoPresenter, BasePresenter() {
    override fun saveInformation(birthday: String, sex: Constants.Sex) {
        TODO("Not yet implemented")
    }

    override fun loadUserInfo() {
        (baseView as IUserInfoView).apply {
            initProfileView(userInfo.displayName,
                CommonUtils.getDateDisplayForUser(userInfo.birthday), userInfo.sex, handleAddress(userInfo.address))
            hideCurrentUserActions()
            if (userInfo.isCurrentUser) {
                showCurrentUserActions()
            }
            hideDisplayNameInput()
            hideSexRadioButton()
        }
    }

    private fun handleAddress(address: AddressRes): String? {
        return when {
            address.province.id < 0 -> null
            address.district.id < 0 -> address.province.name
            address.ward.id < 0 -> "${address.district.name}, ${address.province.name}"
            else -> "${address.district.name}, ${address.province.name}, ${address.ward.name}"
        }
    }
}