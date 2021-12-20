package com.example.social_network_android.ui.home.profile.address

import com.example.social_network_android.data.api.protected.model.Address
import com.example.social_network_android.ui.base.IBaseView

interface IAddressView: IBaseView {
    fun showTitle(flag: Int)
    fun showInitAddressList(address: List<Address>)
    fun closeDialogAndSetResult(position: Int, requestKey: String)
}