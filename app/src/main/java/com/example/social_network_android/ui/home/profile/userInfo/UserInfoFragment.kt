package com.example.social_network_android.ui.home.profile.userInfo

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.social_network_android.R
import com.example.social_network_android.data.api.protected.model.Address
import com.example.social_network_android.data.api.protected.model.UserInfo
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.base.BaseFragment
import com.example.social_network_android.ui.home.profile.address.AddressFragment
import com.example.social_network_android.ui.home.profile.address.AddressFragment.Companion.ADDRESS_KEY
import com.example.social_network_android.utils.Constants
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
        userInfoPresenter = UserInfoPresenter(userInfo!!).also { it ->
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
        onBirthdayEditBtnClick()
        onProvinceClick()
        onDistrictClick()
        onWardClick()
        onAddressSelected()
        onProvinceResetBtnClick()
        onDistrictResetBtnClick()
        onWardResetBtnClick()
        onCheckChange(sex_radio_btn)
        onSaveBtnClick()
        onAddressEditBtnClick()
        onCancelClick()
    }
    private fun onCancelClick(){
        cancel_action.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
    override fun initProfileView(
        _displayName: String,
        _birthday: String,
        _sex: Int,
        _address: String?,
    ) {
        display_name.text = _displayName
        display_name_input.setText(_displayName)
        sex.text = getSexString(_sex)
        sex_radio_btn.checkRadioButton(_sex)
        birthday.text = _birthday
        address.text = _address
    }

    override fun returnProfileScreen() {
        Toast.makeText(requireContext(), "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show()
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun showDisplayNameInput(displayName: String) {
        if (!display_name_input.isVisible) {
            display_name_input.visibility = View.VISIBLE
        }
    }

    override fun hideDisplayNameInput() {
        if (display_name_input.isVisible) {
            display_name_input.visibility = View.GONE
        }
    }

    override fun showDisplayName(displayName: String) {
        if (!display_name.isVisible) {
            display_name.apply {
                visibility = View.VISIBLE
                text = displayName
            }
        }
    }

    override fun isDisplayNameVisible(): Boolean = display_name.isVisible

    override fun hideDisplayName() {
        if (display_name.isVisible) {
            display_name.visibility = View.GONE
        }
    }

    override fun showSexRadioButton(sexNumber: Int) {
        if (!sex_radio_btn.isVisible) {
            sex_radio_btn.apply {
                visibility = View.VISIBLE
                checkRadioButton(sexNumber)
            }
        }
    }

    override fun hideSexRadioButton() {
        if (sex_radio_btn.isVisible) {
            sex_radio_btn.visibility = View.GONE
        }

    }

    override fun isSexVisible(): Boolean = sex.isVisible

    override fun showSex(sexNumber: Int) {
        if (!sex.isVisible) {
            sex.apply {
                visibility = View.VISIBLE
                text = getSexString(sexNumber)
            }
        }
    }

    override fun hideSex() {
        if (sex.isVisible) {
            sex.visibility = View.GONE
        }
    }

    override fun showBirthday(birthdayString: String) {
        birthday.text = birthdayString
    }

    override fun hideBirthday() {
        TODO("Not yet implemented")
    }

    override fun isAddressVisible(): Boolean = address.isVisible

    override fun showAddress(addressString: String?) {
        if (!address.isVisible) {
            address.apply {
                visibility = View.VISIBLE
                text = addressString
            }
        }
    }

    override fun hideAddress() {
        if (address.isVisible) {
            address.visibility = View.GONE
        }
    }

    override fun showAddressEdit(province: String, district: String, ward: String) {
        if (!address_edit_area.isVisible) {
            address_edit_area.visibility = View.VISIBLE
        }
    }

    override fun hideAddressedit() {
        if (address_edit_area.isVisible) {
            address_edit_area.visibility = View.GONE
        }
    }

    override fun showProvince(provinceName: String?) {
        province.text = getString(R.string.add_province_text)
        provinceName?.let {
            province.text = it
        }
    }

    private fun RadioGroup.checkRadioButton(sexNumber: Int) {
        when (sexNumber) {
            Constants.Sex.MALE.value -> check(R.id.radio_male)
            Constants.Sex.FEMALE.value -> check(R.id.radio_female)
            Constants.Sex.OTHER.value -> check(R.id.radio_other)
        }
    }

    private fun onBirthdayEditBtnClick() {
        birthday_edit_btn.setOnClickListener {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    birthday.text = "Ngày $dayOfMonth, tháng ${monthOfYear + 1}, $year"
                    val day = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth
                    val month = if (monthOfYear + 1 < 10) "0${monthOfYear + 1}" else monthOfYear + 1
                    val birthdayString = "$year-$month-$day"
                    userInfoPresenter!!.apply {
                        editBirthdayString(birthdayString)
                        onBirthdaySelected()
                    }
                }
            val dateSplit = userInfoPresenter!!.getBirthdayString().split("-")
            val year = dateSplit[0].toInt();
            val month = (dateSplit[1]).toInt() - 1;
            val date = dateSplit[2].toInt()
            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                dateSetListener, year, month, date
            )
            datePickerDialog.show()
        }
    }

    override fun showCurrentUserActions() {
        save_btn.visibility = View.VISIBLE
        birthday_edit_btn.visibility = View.VISIBLE
        sex_edit_btn.visibility = View.VISIBLE
        display_name_edit_btn.visibility = View.VISIBLE
    }


    override fun enableSaveButton() {
        save_btn.isEnabled = true
    }

    override fun disableSaveButton() {
        save_btn.isEnabled = false
    }

    override fun enableDistrict(districtName: String?) {
        district.text =
            if (districtName.isNullOrEmpty()) getString(R.string.add_district_text) else districtName

        district.isEnabled = true
        district_reset_area.visibility = View.VISIBLE
    }

    override fun disableDistrict() {
        district.isEnabled = false
        district.text = getString(R.string.add_district_text)
        district_reset_area.visibility = View.GONE
    }

    override fun enableWard(wardName: String?) {
        ward.text =
            if (wardName.isNullOrEmpty()) getString(R.string.add_ward_text) else wardName
        ward.isEnabled = true
        ward_reset_area.visibility = View.VISIBLE
    }

    override fun disableWard() {
        ward.isEnabled = false
        ward.text = getString(R.string.add_ward_text)
        ward_reset_area.visibility = View.GONE
    }

    override fun hideCurrentUserActions() {
        save_btn.visibility = View.GONE
        birthday_edit_btn.visibility = View.GONE
        sex_edit_btn.visibility = View.GONE
        display_name_edit_btn.visibility = View.GONE
    }

    private fun onSaveBtnClick() {
        save_btn.setOnClickListener {
            userInfoPresenter!!.saveInformation()
        }
    }

    private fun onDisplayNameEditBtnClick() {
        display_name_edit_btn.setOnClickListener {
            userInfoPresenter!!.apply {
                editDisplayName(display_name_input.text.toString())
                onDisplayNameEditClick()
            }
        }
    }

    private fun onSexEditBtnClick() {
        sex_edit_btn.setOnClickListener {
            enableSaveButton()
            userInfoPresenter!!.onSexRadioClick()
        }
    }

    private fun onSexRadioButtonClick(view: RadioButton) {
        val checked = view.isChecked
        when (view.id) {
            R.id.radio_male ->
                if (checked) {
                    userInfoPresenter!!.editSex(Constants.Sex.MALE.value)
                }
            R.id.radio_female ->
                if (checked) {
                    userInfoPresenter!!.editSex(Constants.Sex.FEMALE.value)
                }
            R.id.radio_other ->
                if (checked) {
                    userInfoPresenter!!.editSex(Constants.Sex.OTHER.value)
                }
        }
    }

    private fun onCheckChange(view: RadioGroup) {
        view.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = view.findViewById<RadioButton>(checkedId)
            onSexRadioButtonClick(radioButton)
        }
    }

    private fun showAddress(flag: Int, locationId: Int = -1) {
        val addressFragment = AddressFragment.newInstance(flag, locationId)
        addressFragment.show(requireActivity().supportFragmentManager, addressFragment.tag)
    }

    private fun onProvinceClick() {
        province.setOnClickListener {
            showAddress(Constants.Address.Province.value)
        }
    }

    private fun onDistrictClick() {
        district.setOnClickListener {
            showAddress(Constants.Address.District.value, userInfoPresenter!!.getProvince())
        }
    }

    private fun onWardClick() {
        ward.setOnClickListener {
            showAddress(Constants.Address.Ward.value, userInfoPresenter!!.getDistrict())
        }
    }

    private fun onProvinceResetBtnClick() {
        province_reset_btn.setOnClickListener {
            userInfoPresenter!!.onResetProvince()
        }
    }

    private fun onDistrictResetBtnClick() {
        district_reset_btn.setOnClickListener {
            userInfoPresenter!!.onResetDistrict()
        }
    }

    private fun onWardResetBtnClick() {
        ward_reset_btn.setOnClickListener {
            userInfoPresenter!!.onResetWard()
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

    private fun onAddressSelected() {
        requireActivity().supportFragmentManager.setFragmentResultListener(Constants.Address.Province.toString(),
            this) { _, bundle ->
            val result: Address? = bundle.getParcelable(ADDRESS_KEY)
            result?.let {
                userInfoPresenter!!.apply {
                    editProvince(it.id, it.name)
                    onProvinceSelected()
                }

            }
        }
        requireActivity().supportFragmentManager.setFragmentResultListener(Constants.Address.District.toString(),
            this) { _, bundle ->
            val result: Address? = bundle.getParcelable(ADDRESS_KEY)
            result?.let {
                userInfoPresenter!!.apply {
                    editDistrict(it.id, it.name)
                    onDistrictSelected()
                }
            }
        }
        requireActivity().supportFragmentManager.setFragmentResultListener(Constants.Address.Ward.toString(),
            this) { _, bundle ->
            val result: Address? = bundle.getParcelable(ADDRESS_KEY)
            result?.let {
                userInfoPresenter!!.apply {
                    editWard(it.id, it.name)
                    onWardSelected()
                }
            }
        }
    }

    private fun onAddressEditBtnClick() {
        address_edit_btn.setOnClickListener {
            userInfoPresenter!!.onAddressEditClick()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        userInfo = null
        userInfoPresenter?.onDetach()
        userInfoPresenter = null
    }
}