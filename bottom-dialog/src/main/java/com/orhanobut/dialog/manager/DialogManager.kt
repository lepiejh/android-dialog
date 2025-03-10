package com.orhanobut.dialog.manager

import android.content.Context
import com.orhanobut.dialog.R
import com.orhanobut.dialog.dialog.LoadDialog

class DialogManager private constructor(){

    companion object {
        val instance: DialogManager by lazy { Holder.INSTANCE }
    }

    private object Holder {
        val INSTANCE = DialogManager()
    }

    private var mDialog: LoadDialog? = null

    fun showProgressDialog(context: Context?,hint: String = "加载中...") {
        releaseDialog()
        mDialog = LoadDialog(context, hint,R.style.dialog)
        mDialog?.setCancelable(false)
        mDialog?.show()
    }

    private fun releaseDialog() {
        if (mDialog != null) {
            mDialog?.dismiss()
            mDialog = null
        }
    }

    fun dismiss() {
        if (mDialog != null && mDialog?.isShowing == true) {
            mDialog?.dismiss()
            mDialog = null
        }
    }
}