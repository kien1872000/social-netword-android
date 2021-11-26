package com.example.social_network_android.ui.base

import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.social_network_android.R
import com.example.social_network_android.utils.DialogUtil

abstract class BaseFragment : Fragment(), IBaseView {
    private var loadingDialog: AlertDialog? = null
    override fun showMessage(msg: String) {
    }

    override fun onError() {
        Toast.makeText(requireActivity(), getString(R.string.cant_connect_server), Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {

    }

    override fun showLoading() {
        hideLoading()
        loadingDialog = DialogUtil.showLoadingDialog(requireActivity())
    }

    override fun hideLoading() {
        loadingDialog?.let{
            if(it.isShowing) it.cancel()
        }
    }
    override fun onBadRequestError() {

    }

    override fun onConflictError() {

    }

    override fun onLogout() {

    }

    override fun onNotFoundError() {

    }

    protected fun showFragment(name: String?, fragment: BaseFragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_left_enter,
                R.anim.slide_left_exit,
                R.anim.slide_right_exit,
                R.anim.slide_right_enter
            )
            .addToBackStack(name)
            .replace(R.id.main_id, fragment)
            .commit()
    }
}