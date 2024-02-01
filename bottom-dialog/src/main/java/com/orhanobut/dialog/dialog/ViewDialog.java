package com.orhanobut.dialog.dialog;

import com.orhanobut.dialog.base.BaseDialog;
import com.orhanobut.dialog.base.DialogViewHolder;
import com.orhanobut.dialog.listener.ViewConvertListener;

import androidx.annotation.LayoutRes;

public class ViewDialog extends BaseDialog {

    private ViewConvertListener convertListener;

    public static ViewDialog newInstance()
    {
        ViewDialog dialog = new ViewDialog();
        return dialog;
    }

    /**
     * 设置Dialog布局
     *
     * @param layoutId
     * @return
     */
    public ViewDialog setLayoutId(@LayoutRes int layoutId)
    {
        this.mLayoutResId = layoutId;
        return this;
    }

    @Override
    public int setUpLayoutId()
    {
        return mLayoutResId;
    }

    @Override
    public void convertView(DialogViewHolder holder, BaseDialog dialog)
    {
        if (convertListener != null)
        {

            convertListener.convertView(holder, dialog);
        }
    }

    public ViewDialog setConvertListener(ViewConvertListener convertListener)
    {
        this.convertListener = convertListener;
        return this;
    }
}
