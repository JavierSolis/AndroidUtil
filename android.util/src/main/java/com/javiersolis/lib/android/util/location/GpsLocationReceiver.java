package com.javiersolis.lib.android.util.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 1/11/15.
 */
public class GpsLocationReceiver extends BroadcastReceiver {

    private IGpsLocationReceiver iGpsLocationReceiver;
    public void setiGpsLocationReceiver(IGpsLocationReceiver iGpsLocationReceiver){
        this.iGpsLocationReceiver= iGpsLocationReceiver;
    }

    public IGpsLocationReceiver getiGpsLocationReceiver()
    {
        return this.iGpsLocationReceiver;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
            getiGpsLocationReceiver().changedGpsProviders();
        }
    }
}