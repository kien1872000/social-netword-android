package com.example.social_network_android.ui.home.profile.address

import com.example.social_network_android.data.api.protected.model.Address
import com.example.social_network_android.ui.base.IBasePresenter

interface IAddressPresenter: IBasePresenter {
    fun loadAddress(locationId: Int = -1)
    fun getAddressItem(position: Int): Address?
    fun onAddressItemClick(position: Int)
    fun getAddressList(): List<Address>
}