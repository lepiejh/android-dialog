package com.orhanobut.dialog.listener;

import com.orhanobut.dialog.base.BaseDialog;
import com.orhanobut.dialog.base.DialogViewHolder;

import java.io.Serializable;

public interface ViewConvertListener extends Serializable
{
    long serialVersionUID = System.currentTimeMillis();

    void convertView(DialogViewHolder holder, BaseDialog dialog);
}
