package com.example.social_network_android.ui.base

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment(), IBaseView  {
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
        TODO("Not yet implemented")
    }
    protected fun onUserTyping(edt: EditText?,
                               msgTv: TextView?,
                               parent: View?) {
        edt?.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val content = s.toString().trim()
                if(content.isNullOrEmpty()){
                   msgTv?.let{
                       it.visibility = View.VISIBLE
                   }
                    parent?.visibility = View.INVISIBLE
                }
                else {
                    msgTv?.visibility = View.INVISIBLE
                    parent?.visibility = View.VISIBLE
                }
            }
        } )
    }
}