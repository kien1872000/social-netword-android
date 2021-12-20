package com.example.social_network_android.ui.home.profile.userInfo

import com.example.social_network_android.ui.base.IBaseView

interface IUserInfoView: IBaseView {
    fun showDisplayNameInput(displayName: String)
    fun hideDisplayNameInput()
    fun showDisplayName(displayName: String)
    fun isDisplayNameVisible(): Boolean
    fun hideDisplayName()
    fun showSexRadioButton(sexNumber: Int)
    fun hideSexRadioButton()
    fun isSexVisible(): Boolean
    fun showSex(sexNumber: Int)
    fun hideSex()
    fun showBirthday(birthdayString: String)
    fun hideBirthday()
    fun isAddressVisible(): Boolean
    fun showAddress(addressString: String?)
    fun hideAddress()
    fun showAddressEdit(province: String, district: String, ward: String)
    fun hideAddressedit()
    fun showProvince(provinceName: String?)
    fun showCurrentUserActions()
    fun enableSaveButton()
    fun disableSaveButton()
    fun enableDistrict(districtName: String?)
    fun disableDistrict()
    fun enableWard(wardName: String?)
    fun disableWard()
    fun hideCurrentUserActions()
    fun initProfileView(_displayName: String, _birthday: String, _sex: Int, _address: String?)
    fun returnProfileScreen()
}