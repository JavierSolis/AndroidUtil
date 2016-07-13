package com.javiersolis.lib.android.util.connection;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 1/11/15.
 */
public class UtilConnection {

    public static BroadcastReceiver registerReceiverConnection(Activity activity, final IConnectionReceiver iConnectionReceiver)
    {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                iConnectionReceiver.changedConnectionProviders();
            }
        };
        activity.registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        return broadcastReceiver;

    }
    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static boolean isOnlinePing(Context context) {

        Runtime runtime = Runtime.getRuntime();
        try
        {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        }
        catch (IOException e)
        { e.printStackTrace(); }
        catch (InterruptedException e)
        { e.printStackTrace(); }

        return false;
    }

    public static boolean isOnlinePingHost(Context context, String host) {

        Runtime runtime = Runtime.getRuntime();
        try
        {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 "+host);
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        }
        catch (IOException e)
        { e.printStackTrace(); }
        catch (InterruptedException e)
        { e.printStackTrace(); }

        return false;
    }
}
