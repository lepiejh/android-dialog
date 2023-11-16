package com.orhanobut.dialog.manager;

import android.content.Context;

import com.orhanobut.dialog.R;
import com.orhanobut.dialog.dialog.LoadDialog;


/**
 * Created by SLAN on 2016/7/8.
 * <p/>
 */
public enum DialogManager
{
    INSTANCE;

    private LoadDialog mDialog;

    public void showProgressDialog(Context context)
    {
        releaseDialog();
        mDialog = new LoadDialog(context, R.style.dialog);
        mDialog.setCancelable(false);

        mDialog.show();
    }

    private void releaseDialog()
    {
        if (mDialog != null)
        {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public void dismiss()
    {
        if (mDialog != null && mDialog.isShowing())
        {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
