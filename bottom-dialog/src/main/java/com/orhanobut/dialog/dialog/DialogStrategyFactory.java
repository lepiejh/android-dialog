package com.orhanobut.dialog.dialog;

import com.ved.framework.base.IBaseView;

public class DialogStrategyFactory {
    public static IDialogStrategy createStrategy(IBaseView<?, ?> viewDelegate) {
        if (viewDelegate.customDialog()) {
            return new CustomDialogStrategy(viewDelegate);
        } else if (viewDelegate.mvvmDialog()) {
            return new MvvmDialogStrategy(viewDelegate);
        } else {
            return new DefaultDialogStrategy(viewDelegate);
        }
    }
}
