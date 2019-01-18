package com.clintagency.adtech;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactApplicationContext;

import com.millennialmedia.InlineAd;
import com.millennialmedia.InlineAd.AdSize;
import com.millennialmedia.MMException;

import java.util.Map;
import java.util.HashMap;


class ADTechInlineView extends LinearLayout {
    public static final String TAG = "MMSDKADTechInlineView";
    public static final HashMap<String, LinearLayout> inlineAds = new HashMap<>();


    Activity mActivity;
    Context mContext;
    LinearLayout inlineAdContainer;

    public ADTechInlineView(final Context context, Activity activity) {
        super(context);

        mActivity = activity;
        mContext = context;

        setLayoutParams(new android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void sendEvent(String name, @Nullable WritableMap event) {
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                name,
                event);
    }

    public void setOptions(ReadableMap options) {
        this.createAd(options);
    };

        @Override
    public void requestLayout() {
        super.requestLayout();

        // The spinner relies on a measure + layout pass happening after it calls requestLayout().
        // Without this, the widget never actually changes the selection and doesn't call the
        // appropriate listeners. Since we override onLayout in our ViewGroups, a layout pass never
        // happens after a call to requestLayout, so we simulate one here.
        post(measureAndLayout);
    }

    private final Runnable measureAndLayout = new Runnable() {
        @Override
        public void run() {
            measure(
                    MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };

    private void createAd(final ReadableMap options) {
        LayoutParams lp;
        AdSize size;

        switch (options.getInt("size")) {
            case 0:
                size = AdSize.BANNER;
                break;
            case 1:
                size = AdSize.LARGE_BANNER;
                break;
            case 2:
                size = AdSize.MEDIUM_RECTANGLE;
                break;
            case 3:
                size = AdSize.FULL_BANNER;
                break;
            case 4:
                size = AdSize.LEADERBOARD;
                break;
            default:
                size = AdSize.SMART_BANNER;
                break;
        }


        inlineAdContainer = ADTechInlineView.inlineAds.get(options.getString("identifier"));

        if (inlineAdContainer != null) {
            if (inlineAdContainer.getParent() != null) {
                ((ViewGroup)inlineAdContainer.getParent()).removeView(inlineAdContainer);
            }
            this.addView(inlineAdContainer);

            //this.requestLayout();

            final InlineAd.InlineAdMetadata inlineAdMetadata = new InlineAd.InlineAdMetadata().
                    setAdSize(size);

            WritableMap event = Arguments.createMap();
            event.putInt("width", inlineAdMetadata.getAdSize().width);
            event.putInt("height", inlineAdMetadata.getAdSize().height);
            ReactContext reactContext = (ReactContext)getContext();
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                    getId(),
                    "topSizeChange",
                    event);

            return;
        }


        try {

            inlineAdContainer = new LinearLayout (mContext);
            this.addView(inlineAdContainer);

            InlineAd inlineAd = InlineAd.createInstance(options.getString("placementId"), inlineAdContainer);

            inlineAd.setListener(new InlineAd.InlineListener() {
                @Override
                public void onRequestSucceeded(InlineAd inlineAd) {
                    ADTechInlineView.inlineAds.put(options.getString("identifier"), inlineAdContainer);
                }


                @Override
                public void onRequestFailed(InlineAd inlineAd, InlineAd.InlineErrorStatus errorStatus) {
                    Log.i(TAG, errorStatus.toString());
                }


                @Override
                public void onClicked(InlineAd inlineAd) {
                    Log.i(TAG, "Inline Ad clicked.");
                }


                @Override
                public void onResize(InlineAd inlineAd, int width, int height) {

                    Log.i(TAG, "Inline Ad starting resize.");
                }


                @Override
                public void onResized(InlineAd inlineAd, int width, int height, boolean toOriginalSize) {

                    Log.i(TAG, "Inline Ad resized.");
                }


                @Override
                public void onExpanded(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad expanded.");
                }


                @Override
                public void onCollapsed(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad collapsed.");
                }


                @Override
                public void onAdLeftApplication(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad left application.");
                }
            });

            if (inlineAd != null) {
                final InlineAd.InlineAdMetadata inlineAdMetadata = new InlineAd.InlineAdMetadata().
                        setAdSize(size);

                inlineAd.request(inlineAdMetadata);

                WritableMap event = Arguments.createMap();
                event.putInt("width", inlineAdMetadata.getAdSize().width);
                event.putInt("height", inlineAdMetadata.getAdSize().height);
                ReactContext reactContext = (ReactContext)getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        getId(),
                        "topSizeChange",
                        event);


            }

        } catch (MMException e) {
            Log.e(TAG, "Error creating inline ad", e);
        }
    }
}

public class ADTechInlineManager extends ViewGroupManager<ADTechInlineView>  {
    public static final String REACT_CLASS = "ADTechInline";
    public static final String TAG = "MMSDKADTechInlineManager";
    private RNAdTechModule mContextModule;

    public ADTechInlineManager(ReactApplicationContext reactContext) {
        mContextModule = new RNAdTechModule(reactContext);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public ADTechInlineView createViewInstance(ThemedReactContext context){
        return new ADTechInlineView(context, mContextModule.getActivity());
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

    @ReactProp(name = "options")
    public void setOptions(ADTechInlineView view, ReadableMap options) {
        Log.i(TAG, "setOptions: " + options);

        view.setOptions(options);


    }
}
