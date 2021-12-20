package com.example.social_network_android.ui.home.profile.address

import android.util.Log
import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.protected.ProtectedApi
import com.example.social_network_android.data.api.protected.model.Address
import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.utils.Constants
import retrofit2.Call
import retrofit2.Response

class AddressPresenter(private val addressFlag: Int) : IAddressPresenter, BasePresenter() {
    private var addresses: List<Address>? = null

    override fun loadAddress(locationId: Int) {
        if (!addresses.isNullOrEmpty()) return
        baseView!!.showLoading()
        val callBack = getCallBack(addressFlag, locationId)
        callBack.enqueue(object : retrofit2.Callback<List<Address>> {
            override fun onResponse(call: Call<List<Address>>, response: Response<List<Address>>) {
                baseView!!.hideLoading()
                if (response.isSuccessful) {
                    addresses = response.body()
                    (baseView as IAddressView).showInitAddressList(addresses!!)
                } else {
                    handleApiError(response.code(), response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<List<Address>>, t: Throwable) {
                baseView!!.hideLoading()
            }

        })
    }

    override fun getAddressItem(position: Int) = addresses!![position]
    override fun onAddressItemClick(position: Int) {
        when (addressFlag) {
            Constants.Address.Province.value -> (baseView as IAddressView).closeDialogAndSetResult(
                position,
                Constants.Address.Province.toString())
            Constants.Address.District.value -> (baseView as IAddressView).closeDialogAndSetResult(
                position,
                Constants.Address.District.toString())
            Constants.Address.Ward.value -> (baseView as IAddressView).closeDialogAndSetResult(
                position,
                Constants.Address.Ward.toString())
        }
    }

    override fun getAddressList(): List<Address> = addresses!!

    private fun getCallBack(addressFlag: Int, locationId: Int = -1): Call<List<Address>> {
        val protectedApi = AppApi.create(Constants.ApiType.ProtectedApi,
            preferencesHelper!!.getAccessToken()) as ProtectedApi
        return when (addressFlag) {
            Constants.Address.Province.value -> protectedApi.getProvinces()
            Constants.Address.District.value -> protectedApi.getDistricts(locationId)
            Constants.Address.Ward.value -> protectedApi.getWards(locationId)
            else -> protectedApi.getProvinces()
        }
    }

}