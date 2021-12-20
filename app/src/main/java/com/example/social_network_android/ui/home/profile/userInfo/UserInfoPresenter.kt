package com.example.social_network_android.ui.home.profile.userInfo

import android.util.Log
import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.protected.ProtectedApi
import com.example.social_network_android.data.api.protected.model.*
import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.utils.CommonUtils
import com.example.social_network_android.utils.Constants
import retrofit2.Call
import retrofit2.Response

class UserInfoPresenter(private val userInfo: UserInfo) : IUserInfoPresenter, BasePresenter() {
    private var displayName: String = userInfo.displayName
    private var sexNumber: Int = userInfo.sexNumber
    private var birthdayString: String = userInfo.birthday
    private var province: Address = userInfo.address.province
    private var district: Address = userInfo.address.district
    private var ward: Address = userInfo.address.ward
    override fun saveInformation(
    ) {
        val protectedApi =
            AppApi.create(Constants.ApiType.ProtectedApi,
                preferencesHelper!!.getAccessToken()) as ProtectedApi
        baseView!!.showLoading()
        val userInfoReq =
            UserInfoReq(birthdayString, sexNumber, AddressReq(province.id, district.id, ward.id))
        Log.d("hhh11", userInfoReq.toString())
        protectedApi.updateUserInfo(userInfoReq)
            .enqueue(object : retrofit2.Callback<UserProfileRes> {
                override fun onResponse(
                    call: Call<UserProfileRes>,
                    response: Response<UserProfileRes>,
                ) {
                    baseView!!.hideLoading()
                    if (response.isSuccessful) {
                        (baseView as IUserInfoView).returnProfileScreen()
                    } else {
                        handleApiError(response.code(), response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<UserProfileRes>, t: Throwable) {
                    baseView!!.hideLoading()
                    handleApiError(t = t)
                }

            })

    }

    override fun loadUserInfo() {
        (baseView as IUserInfoView).apply {
            initProfileView(userInfo.displayName,
                CommonUtils.getDateDisplayForUser(userInfo.birthday),
                userInfo.sexNumber,
                handleAddress(userInfo.address))
            hideCurrentUserActions()
            if (userInfo.isCurrentUser) {
                showCurrentUserActions()
            }
            hideAddressedit()
            showAddress(getAddressString())
            disableSaveButton()
            hideSexRadioButton()
            hideDisplayNameInput()
            showProvince(province.name)
            enableDistrict(district.name)
            enableWard(ward.name)
            if (province.id < 0) {
                showProvince(null)
                disableDistrict()
                disableWard()
            }
            else if(district.id<0 && ward.id<0) {
                showProvince(null)
                enableDistrict(null)
                disableWard()
            }
            else if(district.id>0 && ward.id<0){
                enableWard(null)
            }
        }
    }

    override fun loadDisplayName() {
        (baseView as IUserInfoView).showDisplayName(displayName)
    }

    override fun loadSex() {
        (baseView as IUserInfoView).showSex(sexNumber)
    }

    override fun loadAddress() {
        TODO("Not yet implemented")
    }

    override fun onAddressClick(addressFlag: Int) {

    }

    override fun onDisplayNameEditClick() {
        val view = baseView as IUserInfoView
        view.apply {
            enableSaveButton()
            if (!isDisplayNameVisible()) {
                showDisplayName(displayName)
                hideDisplayNameInput()
            } else {
                hideDisplayName()
                showDisplayNameInput(displayName)
            }
        }
    }

    override fun onAddressEditClick() {
        val view = baseView as IUserInfoView
        view.apply {
            enableSaveButton()
            if (!view.isAddressVisible()) {
                showAddress(getAddressString())
                hideAddressedit()
            } else {
                showAddressEdit(province.name, district.name, ward.name)
                hideAddress()
            }
        }
    }

    private fun getAddressString(): String? {
        if (province.id < 0 && district.id < 0 && ward.id < 0) return null
        else if (province.id > 0 && district.id < 0 && ward.id < 0) return province.name
        else if (province.id > 0 && district.id > 0 && ward.id < 0) return "${province.name}, ${district.name}"
        return "${province.name}, ${district.name}, ${ward.name}"
    }

    override fun onSexRadioClick() {
        val view = baseView as IUserInfoView
        view.apply {
            enableSaveButton()
            if (!isSexVisible()) {
                showSex(sexNumber)
                hideSexRadioButton()
            } else {
                hideSex()
                showSexRadioButton(sexNumber)
            }
        }
    }

    override fun getDisplayName(): String = displayName

    override fun editDisplayName(displayName: String) {
        if (displayName.trim().isNotEmpty()) {
            this.displayName = displayName
        }
    }

    override fun getBirthdayString(): String = userInfo.birthday
    override fun editBirthdayString(birthdayString: String) {
        this.birthdayString = birthdayString
    }

    override fun getSex() {
        TODO("Not yet implemented")
    }

    override fun editSex(sexNumber: Int) {
        this.sexNumber = sexNumber
    }

    override fun getProvince(): Int = province.id
    override fun editProvince(provinceId: Int, provinceName: String) {
        province = Address(provinceId, provinceName)
    }

    override fun getDistrict(): Int = district.id

    override fun editDistrict(districtId: Int, districtName: String) {
        district = Address(districtId, districtName)
    }

    override fun getWard() {
        TODO("Not yet implemented")
    }

    override fun editWard(wardId: Int, wardName: String) {
        ward = Address(wardId, wardName)
    }

    override fun onBirthdaySelected() {
        (baseView as IUserInfoView).apply {
            enableSaveButton()
            showBirthday(CommonUtils.getDateDisplayForUser(birthdayString))
        }

    }

    override fun onProvinceSelected() {
        val view = baseView as IUserInfoView
        val provinceName = if (province.id < 0) null else province.name
        editDistrict(-1, "")
        editWard(-1, "")
        view.apply {
            showProvince(provinceName)
            enableDistrict(null)
            disableWard()
        }
    }

    override fun onDistrictSelected() {
        val view = baseView as IUserInfoView
        val districtName = if (district.id < 0) null else district.name
        editWard(-1, "")
        view.apply {
            enableDistrict(districtName)
            enableWard(null)
        }
    }

    override fun onWardSelected() {
        val view = baseView as IUserInfoView
        val wardName = if (ward.id < 0) null else ward.name
        view.enableDistrict(wardName)
    }

    override fun onResetProvince() {
        val view = baseView as IUserInfoView
        editProvince(-1, "")
        editDistrict(-1, "")
        editWard(-1, "")
        view.apply {
            showProvince(null)
            disableDistrict()
            disableWard()
        }
    }

    override fun onResetDistrict() {
        val view = baseView as IUserInfoView
        editDistrict(-1, "")
        editWard(-1, "")
        view.apply {
            enableDistrict(null)
            disableWard()
        }
    }

    override fun onResetWard() {
        val view = baseView as IUserInfoView
        editWard(-1, "")
        view.apply {
            enableWard(null)
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

    private fun getSexString(sexNumber: Int): String {
        return when (sexNumber) {
            Constants.Sex.MALE.value -> "Nam"
            Constants.Sex.FEMALE.value -> "Nữ"
            Constants.Sex.OTHER.value -> "Khác"
            else -> "Khác"
        }
    }
}