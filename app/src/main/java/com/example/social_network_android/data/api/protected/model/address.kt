package com.example.social_network_android.data.api.protected.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class AddressReq(val province: Int, val district: Int, val ward: Int)
@Parcelize
data class Address(@SerializedName("_id")val  id : Int, val name: String): Parcelable
@Parcelize
data class AddressRes(val province: Address, val district: Address, val ward: Address): Parcelable
