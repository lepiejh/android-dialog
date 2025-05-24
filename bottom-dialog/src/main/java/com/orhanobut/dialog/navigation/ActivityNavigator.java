package com.orhanobut.dialog.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ActivityNavigator {
    private final Context context;
    private Class<?> target;
    private Bundle bundle;
    private int flags = -1;

    private ActivityNavigator(Context context) {
        this.context = context;
    }

    public static ActivityNavigator with(Context context) {
        return new ActivityNavigator(context);
    }

    public ActivityNavigator target(Class<?> target) {
        this.target = target;
        return this;
    }

    public ActivityNavigator bundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public ActivityNavigator addFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public ActivityNavigator putExtra(String key, String value) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(key, value);
        return this;
    }

    public void navigate() {
        if (target == null) {
            throw new IllegalArgumentException("Target activity class must be specified");
        }

        Intent intent = new Intent(context, target);

        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (flags != -1) {
            intent.addFlags(flags);
        }

        context.startActivity(intent);
    }
}
