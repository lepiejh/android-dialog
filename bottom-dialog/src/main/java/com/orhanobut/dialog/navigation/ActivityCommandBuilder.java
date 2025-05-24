package com.orhanobut.dialog.navigation;

import android.os.Bundle;

import com.ved.framework.bus.event.SingleLiveEvent;
import com.ved.framework.entity.ParameterField;

import java.util.HashMap;
import java.util.Map;

public class ActivityCommandBuilder {
    private Class<?> target;
    private Bundle bundle;
    private String canonicalName;
    private int requestCode = -1;

    static ActivityCommandBuilder create() {
        return new ActivityCommandBuilder();
    }

    ActivityCommandBuilder setTarget(Class<?> target) {
        this.target = target;
        return this;
    }

    ActivityCommandBuilder setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    ActivityCommandBuilder setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
        return this;
    }

    ActivityCommandBuilder setRequestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    void execute(SingleLiveEvent<Map<String, Object>> liveEvent) {
        Map<String, Object> params = new HashMap<>();
        if (target != null) {
            params.put(ParameterField.CLASS, target);
        }
        if (bundle != null) {
            params.put(ParameterField.BUNDLE, bundle);
        }
        if (canonicalName != null) {
            params.put(ParameterField.CANONICAL_NAME, canonicalName);
        }
        if (requestCode != -1) {
            params.put(ParameterField.REQUEST_CODE, requestCode);
        }
        liveEvent.postValue(params);
    }
}
