package com.example.social_network_android.ui.home.signup.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import com.example.social_network_android.R
import kotlinx.android.synthetic.main.fragment_sex.*
import android.content.res.ColorStateList
import android.graphics.Color

import android.os.Build





private const val DISPLAY_NAME = "displayName"
private const val BIRTHDAY = "birthday"

class SexFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var sex: String? = null
    private var displayName: String? = null
    private var birthday: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            displayName = it.getString(DISPLAY_NAME)
            birthday = it.getString(BIRTHDAY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radio_btn_signup_sex.setOnCheckedChangeListener { group, checkedId ->

            val radioButton = view.findViewById<RadioButton>(checkedId)
            onRadioButtonClicked(radioButton)
        }
    }
    fun onRadioButtonClicked(view: RadioButton) {

            // Is the button now checked?
            val checked = view.isChecked
            setRBColorAfterCheck(view, resources.getColor(R.color.line_color))

        // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_male ->
                    if (checked) {
                        setRBColorAfterCheck(view, resources.getColor(R.color.forgot_password))
                        Toast.makeText(activity, "male", Toast.LENGTH_SHORT).show()
                    }
                R.id.radio_female ->
                    if (checked) {
                        setRBColorAfterCheck(view, resources.getColor(R.color.forgot_password))
                        Toast.makeText(activity, "female", Toast.LENGTH_SHORT).show()
                    }
                R.id.radio_other ->
                    if (checked) {
                        setRBColorAfterCheck(view, resources.getColor(R.color.forgot_password))
                        Toast.makeText(activity, "female", Toast.LENGTH_SHORT).show()
                    }
            }

    }
    companion object {
        @JvmStatic
        fun newInstance(displayName: String, birthday: String) =
            SexFragment().apply {
                arguments = Bundle().apply {
                    putString(DISPLAY_NAME, displayName)
                    putString(BIRTHDAY, birthday)
                }
            }
    }
    private fun setRBColorAfterCheck(view: RadioButton, colorRes: Int){
        if (Build.VERSION.SDK_INT >= 21) {
            val colorStateList = ColorStateList(
                arrayOf(
                    intArrayOf(-android.R.attr.state_enabled),
                    intArrayOf(android.R.attr.state_enabled)
                ), intArrayOf(
                    colorRes,  // disabled
                    colorRes // enabled
                )
            )
            view.buttonTintList = colorStateList // set the color tint list
            view.invalidate() // Could not be necessary
        }
    }
}