package com.example.social_network_android.ui.home.profile.address

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.social_network_android.R
import com.example.social_network_android.data.api.protected.model.Address
import com.example.social_network_android.data.api.protected.model.AddressRes
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.base.BaseFragment
import com.example.social_network_android.utils.CommonUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_address.*
import com.google.android.material.bottomsheet.BottomSheetBehavior

import android.widget.FrameLayout

import android.content.DialogInterface
import android.content.DialogInterface.OnShowListener
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.social_network_android.utils.CommonUtils.removeAccents
import com.example.social_network_android.utils.Constants


private const val LOCATION_ID = "locationId"
private const val ADDRESS_FLAG = "address_flag"


class AddressFragment : BottomSheetDialogFragment(), IAddressView, OnAddressItemClickListener {
    private var locationId = -1
    private var addressFlag = 0
    private var addressPresenter: AddressPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            locationId = it.getInt(LOCATION_ID)
            addressFlag = it.getInt(ADDRESS_FLAG)
        }
        addressPresenter = AddressPresenter(addressFlag).also {
            it.onAttach(this, PreferencesHelper(requireContext()))
        }

    }

    companion object {
        const val ADDRESS_KEY = "address_key"
        @JvmStatic
        fun newInstance(addressFlag: Int, locationId: Int = -1) =
            AddressFragment().apply {
                arguments = Bundle().apply {
                    putInt(ADDRESS_FLAG, addressFlag)
                    putInt(LOCATION_ID, locationId)
                }
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addressPresenter!!.loadAddress(locationId)
    }

    override fun onResume() {
        super.onResume()
        onCloseDialogClick()
        onUserTyping()
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun showTitle(flag: Int) {
        TODO("Not yet implemented")
    }

    override fun showInitAddressList(address: List<Address>) {
        address_list.adapter = AddressAdapter(address.toMutableList(), this)
        address_list.layoutManager = LinearLayoutManager(requireContext())
        address_list.setHasFixedSize(true)
    }


    override fun showMessage(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onError() {
        Toast.makeText(requireContext(), R.string.cant_connect_server, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        if (!loading.isVisible) loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        if (loading.isVisible) loading.visibility = View.GONE
    }

    override fun onBadRequestError() {
        TODO("Not yet implemented")
    }

    override fun onUnAuthorizeError() {
        TODO("Not yet implemented")
    }

    private fun onCloseDialogClick() {
        close_dialog.setOnClickListener {
            this.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addressPresenter = null
    }

    override fun onAddressItemClick(position: Int) {
       addressPresenter!!.onAddressItemClick(position)
    }
    override fun closeDialogAndSetResult(position: Int, requestKey: String) {
        val address = (address_list.adapter as AddressAdapter).getItem(position)
        requireActivity().supportFragmentManager.setFragmentResult(requestKey,
            bundleOf(ADDRESS_KEY to address))
        this.dismiss()
    }
    private fun onUserTyping() {
        search_input.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val searchContent = s.toString().trim().lowercase()
                if(searchContent.isEmpty()) address_list.scrollToPosition(0)
                val adapter = (address_list.adapter) as AddressAdapter
                val addressList = addressPresenter!!.getAddressList().toMutableList()
                val searchList =
                    addressList.filter {
                        (removeAccents(it.name.lowercase())).contains(
                            removeAccents(
                                searchContent
                            )
                        )
                    }
                adapter.updateAddresses(searchList)
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })
    }
}
