package com.example.social_network_android.ui.base

import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.social_network_android.R
import com.example.social_network_android.utils.DialogUtil

abstract class BaseFragment : Fragment(), IBaseView {
    private var loadingDialog: AlertDialog? = null
    override fun showMessage(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onError(error: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(msg: String) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override fun onConflictError() {
        TODO("Not yet implemented")
    }

    override fun onLogout() {
        TODO("Not yet implemented")
    }

    override fun onNotFoundError() {
        TODO("Not yet implemented")
    }

    override fun onServerError() {
        TODO("Not yet implemented")
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