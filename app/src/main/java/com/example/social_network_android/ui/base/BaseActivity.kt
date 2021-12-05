package com.example.social_network_android.ui.base

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.social_network_android.R
import com.example.social_network_android.utils.DialogUtil

abstract class BaseActivity : AppCompatActivity(), IBaseView {
    private var loadingDialog: AlertDialog? = null
    override fun showLoading() {
        hideLoading()
        loadingDialog = DialogUtil.showLoadingDialog(this)
    }

    override fun showMessage(msg: String) {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        loadingDialog?.let{
            if(it.isShowing) it.cancel()
        }
    }
    override fun onError() {
        Toast.makeText(this, getString(R.string.cant_connect_server), Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onBadRequestError() {
        TODO("Not yet implemented")
    }

    override fun onConflictError() {
        TODO("Not yet implemented")
    }


    override fun onNotFoundError() {
        TODO("Not yet implemented")
    }

    override fun onUnAuthorizeError() {
        TODO("Not yet implemented")
    }
}