package com.clintagency.adtech;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.util.Log;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
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
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactApplicationContext;

import com.millennialmedia.InterstitialAd;
import com.millennialmedia.MMException;

import java.util.Map;

import com.clintagency.adtech.RNAdTechModule;


class ADTechInterstitialView extends LinearLayout {
    public static final String TAG = "MMSDKADTechInterstitialView";
    protected InterstitialAd interstitialAd;

    Activity mActivity;
    Context mContext;

    public ADTechInterstitialView(final Context context, Activity activity) {
        super(context);

        mActivity = activity;
        mContext = context;

        setLayoutParams(new android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setPlacementId(String placementId) {
        LayoutParams lp;

        Log.i(TAG, "Interstitial Ad loaded.");


        try {
            interstitialAd = InterstitialAd.createInstance(placementId);

            interstitialAd.setListener(new InterstitialAd.InterstitialListener() {
                @Override
                public void onLoaded(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad loaded.");
                    try {
                        interstitialAd.show(mActivity);
                    } catch (MMException e) {
                        Log.i(TAG, "Unable to show interstitial ad content, exception occurred");
                        e.printStackTrace();
                    }

                }


                @Override
                public void onLoadFailed(InterstitialAd interstitialAd,
                                         InterstitialAd.InterstitialErrorStatus errorStatus) {

                    Log.i(TAG, "Interstitial Ad load failed.");
                }


                @Override
                public void onShown(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad shown.");
                }


                @Override
                public void onShowFailed(InterstitialAd interstitialAd,
                                         InterstitialAd.InterstitialErrorStatus errorStatus) {

                    Log.i(TAG, "Interstitial Ad show failed.");
                }


                @Override
                public void onClosed(InterstitialAd interstitialAd) {

                    Log.i(TAG, "Interstitial Ad closed.");
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
                }
            });

            if (interstitialAd != null) {
                interstitialAd.load(mActivity, null);
            }

        } catch (MMException e) {
            Log.e(TAG, "Error creating interstitial ad", e);
            // abort loading ad
        }
    };
}

public class ADTechInterstitialManager extends ViewGroupManager<ADTechInterstitialView>  {
    public static final String REACT_CLASS = "ADTechInterstitial";
    public static final String TAG = "MMSDKADTechInterstitialManager";
    private RNAdTechModule mContextModule;

    public ADTechInterstitialManager(ReactApplicationContext reactContext) {
        mContextModule = new RNAdTechModule(reactContext);
    }

    @Override
    public String getName() {
        // Tell React the name of the module
        // https://facebook.github.io/react-native/docs/native-components-android.html#1-create-the-viewmanager-subclass
        return REACT_CLASS;
    }

    @Override
    public ADTechInterstitialView createViewInstance(ThemedReactContext context){
        return new ADTechInterstitialView(context, mContextModule.getActivity());
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put(
                        "topSizeChange",
                        MapBuilder.of(
                                "phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onSizeChange")))
                .build();
    }

    @ReactProp(name = "exampleProp")
    public void setExampleProp(View view, String prop) {
        // Set properties from React onto your native component via a setter method
        // https://facebook.github.io/react-native/docs/native-components-android.html#3-expose-view-property-setters-using-reactprop-or-reactpropgroup-annotation
    }
    
    @ReactProp(name = "placementId")
    public void setPlacementId(ADTechInterstitialView view, String placementId) {
        Log.i(TAG, "setPlacementId: " + placementId);

        view.setPlacementId(placementId);
    }
}
