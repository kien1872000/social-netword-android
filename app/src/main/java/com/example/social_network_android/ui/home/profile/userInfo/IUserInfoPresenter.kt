package com.example.social_network_android.ui.home.profile.userInfo


import com.example.social_network_android.data.api.protected.model.Address
import com.example.social_network_android.ui.base.IBasePresenter
import com.example.social_network_android.ui.base.IBaseView
import com.example.social_network_android.utils.Constants

interface IUserInfoPresenter: IBasePresenter {
    fun saveInformation()
    fun loadUserInfo()
    fun loadDisplayName()
    fun loadSex()
    fun loadAddress()
    fun onAddressClick(addressFlag: Int)
    fun onDisplayNameEditClick()
    fun onAddressEditClick()
    fun onSexRadioClick()
    fun getDisplayName(): String
    fun editDisplayName(displayName: String)
    fun getBirthdayString(): String
    fun editBirthdayString(birthdayString: String)
    fun getSex()
    fun editSex(sexNumber: Int)
    fun getProvince(): Int
    fun editProvince(provinceId: Int, provinceName: String)
    fun getDistrict(): Int
    fun editDistrict(districtId: Int, districtName: String)
    fun getWard()
    fun editWard(wardId: Int, wardName: String)
    fun onBirthdaySelected()
    fun onProvinceSelected()
    fun onDistrictSelected()
    fun onWardSelected()
    fun onResetProvince()
    fun onResetDistrict()
    fun onResetWard()
}