package com.example.social_network_android.ui.home.profile.address

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.social_network_android.R
import com.example.social_network_android.data.api.protected.model.Address
import kotlinx.android.synthetic.main.address_item.view.*

class AddressAdapter(
     val addressList: MutableList<Address>,
    private val onAddressItemClickListener: OnAddressItemClickListener,
) :
    RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val addressName: TextView = view.findViewById(R.id.address_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.address_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressAdapter.ViewHolder, position: Int) {
        holder.addressName.text = addressList[position].name
        holder.itemView.clickable_area.setOnClickListener {
            onAddressItemClickListener.onAddressItemClick(holder.adapterPosition)
        }
    }

    override fun getItemCount() = addressList.size
    fun getItem(position: Int) = addressList[position]
    fun updateAddresses(newAddresses: List<Address>) {
        val diffUtilCallback = AddressDiffUtilCallback(addressList, newAddresses)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        addressList.clear()
        addressList.addAll(newAddresses)
        diffResult.dispatchUpdatesTo(this)
    }
}