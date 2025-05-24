package com.orhanobut.dialog.dialog;

import com.orhanobut.dialog.manager.DialogManager;
import com.ved.framework.base.IBaseView;

class DefaultDialogStrategy implements IDialogStrategy {
    private final IBaseView<?,?> iBaseView;

    public DefaultDialogStrategy(IBaseView<?,?> iBaseView) {
        this.iBaseView = iBaseView;
    }

    @Override
    public void show(String title) {
        DialogManager.Companion.getInstance().showProgressDialog(iBaseView.FragmentActivity(), title);
    }

    @Override
    public void dismiss() {
        DialogManager.Companion.getInstance().dismiss();
    }
}
