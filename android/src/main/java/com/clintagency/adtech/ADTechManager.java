package com.clintagency.adtech;

import android.app.Activity;
import android.os.Handler;
import android.widget.Toast;
import android.util.Log;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import android.widget.LinearLayout.LayoutParams;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.views.view.ReactViewGroup;

import com.millennialmedia.InterstitialAd;
import com.millennialmedia.MMException;
import com.millennialmedia.MMSDK;

import java.util.Map;
import java.util.HashMap;

public class ADTechManager extends ReactContextBaseJavaModule {
    public static final String TAG = "ADTechManager";
    Handler handler;
    InterstitialAd interstitialAd;

    @Override
    public String getName() {
        return TAG;
    }

    public ADTechManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void setGDPR(Boolean activate, String contentstring) {
        MMSDK.setConsentData(MMSDK.IAB_CONSENT_KEY,contentstring);
    }

    @ReactMethod
    public void setAd(Boolean activate, String contentstring) {
        MMSDK.setConsentData(MMSDK.IAB_CONSENT_KEY,contentstring);
    }

    @ReactMethod
    public void showInterstitial(String placementId, Integer autoHide) {

        final Activity activity = getCurrentActivity();

        Log.i(TAG, "showInterstitial: " + activity);

        if (activity == null) {
            return;
        }
            try {
            interstitialAd = InterstitialAd.createInstance(placementId);

            interstitialAd.setListener(new InterstitialAd.InterstitialListener() {
                @Override
                public void onLoaded(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad loaded.");

                    try {
                        interstitialAd.show(activity);
                        handler = new Handler();
                    } catch (MMException e) {
                        Log.i(TAG, "Unable to show interstitial ad content, exception occurred");
                        e.printStackTrace();
                    }
                }


                @Override
                public void onLoadFailed(InterstitialAd interstitialAd,
                                         InterstitialAd.InterstitialErrorStatus errorStatus) {

                    Log.i(TAG, "Interstitial Ad load failed." + errorStatus);
                }


                @Override
                public void onShown(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad shown.");


//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.i(TAG, "Interstitial Ad hide.");
//                            interstitialAd.destroy();
//                        }
//                    }, autoHide);

                }


                @Override
                public void onShowFailed(InterstitialAd interstitialAd,
                                         InterstitialAd.InterstitialErrorStatus errorStatus) {

                    Log.i(TAG, "Interstitial Ad show failed.");
                }


                @Override
                public void onClosed(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad closed.");
                    handler.removeCallbacksAndMessages(null);
                }


                @Override
                public void onClicked(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad clicked.");
                }


                @Override
                public void onAdLeftApplication(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad left application.");
                }


                @Override
                public void onExpired(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad expired.");
                    handler.removeCallbacksAndMessages(null);
                }
            });

            if (interstitialAd != null) {
                interstitialAd.load(activity, null);
            }

        } catch (MMException e) {
            Log.e(TAG, "Error creating interstitial ad", e);
            // abort loading ad
        }
    }

    @ReactMethod
    public void hideInterstitial() {
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
    }
}