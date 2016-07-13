package com.javiersolis.lib.android.util.connection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 1/11/15.
 */
public class ConnectionReceiver extends BroadcastReceiver {

    private IConnectionReceiver iGpsLocationReceiver;
    public void setiGpsLocationReceiver(IConnectionReceiver iGpsLocationReceiver){
        this.iGpsLocationReceiver= iGpsLocationReceiver;
    }

    public IConnectionReceiver getiGpsLocationReceiver()
    {
        return this.iGpsLocationReceiver;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
            getiGpsLocationReceiver().changedConnectionProviders();
        }
    }
}