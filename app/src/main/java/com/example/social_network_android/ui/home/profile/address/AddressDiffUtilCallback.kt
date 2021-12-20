package com.example.social_network_android.ui.home.profile.address

import androidx.recyclerview.widget.DiffUtil
import com.example.social_network_android.data.api.protected.model.Address

class AddressDiffUtilCallback(private val oldList: List<Address>,
                              private val newList: List<Address>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}