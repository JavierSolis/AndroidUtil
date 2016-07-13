package com.javiersolis.lib.android.util.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 10/08/15.
 */
public class AdsBuilderUtil
{
    private static boolean IS_TEST=true;
    private static List<String> DEVICES_TEST ;//= Arrays.asList("F6FEA469CD1F319E847D220A131F2");
    private static boolean IS_CAPTURING=false;


    public static  void setIsTest(boolean isTest)
    {
        IS_TEST=isTest;
    }


    public static  void setIsCapturing(boolean isCapturing)
    {
        IS_CAPTURING=isCapturing;
    }


    public static void addDeviceTest(String deviceId)
    {
        if(DEVICES_TEST==null)
        {
            DEVICES_TEST = new ArrayList<String>();
        }

        DEVICES_TEST.add(deviceId);
    }

    private InterstitialAd interstitialAds;
    private AdsBuilderUtilCallBack adsBuilderUtilCallBack;

    public InterstitialAd newInterstitial(Context context, int adUnitId, AdsBuilderUtilCallBack adBuildCallBack)
    {
        this.adsBuilderUtilCallBack = adBuildCallBack;


        InterstitialAd newInterstitialAds = new InterstitialAd(context);
        newInterstitialAds.setAdUnitId(context.getString(adUnitId));

        newInterstitialAds.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                requestNewInterstitial();
                adsBuilderUtilCallBack.launchMethod();
            }
        });

        this.interstitialAds = newInterstitialAds;

        if(!IS_CAPTURING)
        {
            requestNewInterstitial();
        }

        return newInterstitialAds;
    }

    public void requestNewInterstitial() {
        AdRequest adRequest = makeAdRequestBuilder().build();
        this.interstitialAds.loadAd(adRequest);
    }


    public AdRequest.Builder makeAdRequestBuilder()
    {

        AdRequest.Builder requestBuilder = new AdRequest.Builder();

        if(IS_TEST)
        {
            for (String deviceTest: DEVICES_TEST)
            {
                requestBuilder.addTestDevice(deviceTest);
            }
        }

        return requestBuilder;
    }

    public AdView newAdView (Activity activity, int idAdView )
    {
        AdView mAdView = (AdView) activity.findViewById(idAdView);
        //mAdView.setAdUnitId(activity.getString(adUnitId));
        AdRequest adRequest = makeAdRequestBuilder().build();

        if(!IS_CAPTURING)
        {
            mAdView.loadAd(adRequest);
        }
        return mAdView;
    }

    public AdView newAdView (View view, int idAdView )
    {
        AdView mAdView = (AdView) view.findViewById(idAdView);
        //mAdView.setAdUnitId(activity.getString(adUnitId));
        AdRequest adRequest = makeAdRequestBuilder().build();

        if(!IS_CAPTURING)
        {
            mAdView.loadAd(adRequest);
        }
        return mAdView;
    }


    public AdView newAdView (Activity activity, int idContainer , String adsUnitId)
    {
        AdView mAdView = new AdView(activity);
        mAdView.setAdUnitId(adsUnitId);
        mAdView.setAdSize(AdSize.BANNER);

        LinearLayout layoutContainer = (LinearLayout)activity.findViewById(idContainer);
        layoutContainer.addView(mAdView);

        AdRequest adRequest = makeAdRequestBuilder().build();

        if(!IS_CAPTURING)
        {
            mAdView.loadAd(adRequest);
        }
        return mAdView;
    }

}
