package com.example.social_network_android.ui.home.profile.address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.social_network_android.R
import com.example.social_network_android.data.api.protected.model.AddressRes

private const val ADDRESS = "address"



class AddressFragment : Fragment() {
    private var address: AddressRes? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            address = it.getParcelable(ADDRESS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(address: AddressRes) =
            AddressFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ADDRESS, address)
                }
            }
    }
}