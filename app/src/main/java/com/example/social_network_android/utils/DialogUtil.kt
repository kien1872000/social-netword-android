package com.example.social_network_android.utils

import android.app.AlertDialog
import android.content.Context
import com.example.social_network_android.R
import dmax.dialog.SpotsDialog

class DialogUtil {
    companion object {
        fun showLoadingDialog(context: Context): AlertDialog{
            val loadingDialog = SpotsDialog.Builder().setContext(context)
                .setMessage("Đang tải...")
                .setCancelable(true)
                .setTheme(R.style.LoadingDialogCustom)
                .build()
            loadingDialog.show()
            return loadingDialog
        }
    }
}