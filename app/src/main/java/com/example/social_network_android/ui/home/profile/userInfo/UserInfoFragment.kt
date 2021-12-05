package com.example.social_network_android.ui.home.profile.userInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.social_network_android.R
import com.example.social_network_android.data.api.protected.model.UserInfo
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_info.*


private const val USER_INFO = "user_info"


class UserInfoFragment : BaseFragment(), IUserInfoView {
    private var userInfo: UserInfo? = null
    private var userInfoPresenter: UserInfoPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userInfo = it.getParcelable(USER_INFO)
        }
        userInfoPresenter = UserInfoPresenter(userInfo!!).also {
            it.onAttach(this,
                PreferencesHelper(requireContext()))
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(userInfo: UserInfo) =
            UserInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER_INFO, userInfo)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userInfoPresenter!!.loadUserInfo()
    }

    override fun onResume() {
        super.onResume()
        onDisplayNameEditBtnClick()
        onSexEditBtnClick()
    }
    override fun initProfileView(
        _displayName: String,
        _birthday: String,
        _sex: String,
        _address: String?
    ) {
        display_name.text = _displayName
        sex.text = _sex
        birthday.text = _birthday
        address.text = _address
    }

    override fun showDisplayNameInput() {
        display_name_input.apply {
            visibility = View.VISIBLE
            text = null
        }
        display_name.visibility = View.GONE
    }

    override fun showSexRadioButton() {
        sex_radio_btn.apply {
            visibility = View.VISIBLE
            check(R.id.radio_male)
        }
        sex.visibility = View.GONE
    }

    override fun showAddressFragment() {
        TODO("Not yet implemented")
    }

    override fun showDatePickerDialog() {
        TODO("Not yet implemented")
    }

    override fun showCurrentUserActions() {
        save_btn.visibility = View.VISIBLE
        birthday_edit_btn.visibility = View.VISIBLE
        sex_edit_btn.visibility = View.VISIBLE
        display_name_edit_btn.visibility = View.VISIBLE
    }


    override fun hideSexRadioButton() {
        sex_radio_btn.visibility = View.GONE
        sex.visibility = View.VISIBLE
    }

    override fun hideDisplayNameInput() {
        display_name_input.visibility = View.GONE
        display_name.visibility = View.VISIBLE
    }

    override fun enableSaveButton() {
        if(!save_btn.isEnabled) {
            save_btn.isEnabled = true
            save_btn_effect.setBackgroundResource(R.drawable.img_click_effect)
        }

    }
    override fun disableSaveButton() {
        if(save_btn.isEnabled) {
            save_btn.isEnabled = false
            save_btn_effect.setBackgroundResource(R.color.button_disabled)
        }
    }

    override fun hideCurrentUserActions() {
        save_btn.visibility = View.GONE
        birthday_edit_btn.visibility = View.GONE
        sex_edit_btn.visibility = View.GONE
        display_name_edit_btn.visibility = View.GONE
    }
    private fun onDisplayNameEditBtnClick() {
        display_name_edit_btn.setOnClickListener {
            if(display_name_input.isVisible) hideDisplayNameInput()
            else showDisplayNameInput()
        }
    }
    private fun onSexEditBtnClick() {
        sex_edit_btn.setOnClickListener {
            if(sex_radio_btn.isVisible) hideSexRadioButton()
            else showSexRadioButton()
        }
    }

}