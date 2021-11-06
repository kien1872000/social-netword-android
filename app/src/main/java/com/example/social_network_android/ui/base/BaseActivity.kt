package com.example.social_network_android.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity(), IBaseView {
    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun showMessage(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onError(error: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(msg: String) {
        TODO("Not yet implemented")
    }


}