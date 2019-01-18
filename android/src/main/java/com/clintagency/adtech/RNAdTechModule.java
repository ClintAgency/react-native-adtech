package com.clintagency.adtech;

import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class RNAdTechModule extends ReactContextBaseJavaModule {

    public RNAdTechModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNAdTech";
    }

    public Activity getActivity() {
        return this.getCurrentActivity();
    }
}
