package com.orhanobut.dialog.dialog;

import com.ved.framework.base.IBaseView;

class CustomDialogStrategy implements IDialogStrategy {
    private final IBaseView<?,?> iBaseView;

    public CustomDialogStrategy(IBaseView<?,?> iBaseView) {
        this.iBaseView = iBaseView;
    }

    @Override
    public void show(String title) {
        iBaseView.showCustomDialog();
    }

    @Override
    public void dismiss() {
        iBaseView.dismissCustomDialog();
    }
}
