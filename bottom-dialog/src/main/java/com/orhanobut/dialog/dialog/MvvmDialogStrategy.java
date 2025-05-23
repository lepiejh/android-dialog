package com.orhanobut.dialog.dialog;

import com.mumu.dialog.MMLoading;
import com.ved.framework.base.IBaseView;

public class MvvmDialogStrategy implements IDialogStrategy{
    private MMLoading mmLoading;
    private final IBaseView<?,?> iBaseView;

    public MvvmDialogStrategy(IBaseView<?,?> iBaseView) {
        this.iBaseView = iBaseView;
    }

    @Override
    public void show(String title) {
        if (mmLoading == null) {
            MMLoading.Builder builder = new MMLoading.Builder(iBaseView.FragmentActivity())
                    .setMessage(title)
                    .setShowMessage(true)
                    .setCancelable(false)
                    .setCancelOutside(false);
            mmLoading = builder.create();
        }
        mmLoading.getWindow().setDimAmount(0f);
        mmLoading.show();
    }

    @Override
    public void dismiss() {
        if (mmLoading != null && mmLoading.isShowing()) {
            mmLoading.dismiss();
        }
    }
}