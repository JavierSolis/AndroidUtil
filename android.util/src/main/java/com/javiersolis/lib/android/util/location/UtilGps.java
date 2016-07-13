package com.javiersolis.lib.android.util.location;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 1/11/15.
 */
public class UtilGps {

    public static BroadcastReceiver registerReceiverGps(Activity activity, final IGpsLocationReceiver iGpsLocationReceiver)
    {
        BroadcastReceiver myBc = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                iGpsLocationReceiver.changedGpsProviders();
            }
        };
        activity.registerReceiver(myBc, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));
        return myBc;
    }

    public static void enableGPSasRoot(Context context)
    {
        Intent intent=new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        context.sendBroadcast(intent);
    }

    public static boolean isEnableGps(Activity activity)
    {
        LocationManager mlocManager = (LocationManager) activity
                .getSystemService(Context.LOCATION_SERVICE);

        return mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    public static void enableGPS(final Activity activity)
    {

        if (!isEnableGps(activity)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    activity);
            alertDialogBuilder
                    .setMessage("GPS is disabled in your device. Enable it?")
                    .setCancelable(false)
                    .setPositiveButton("Enable GPS",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Intent callGPSSettingIntent = new Intent(
                                            android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    activity.startActivity(callGPSSettingIntent);
                                }
                            });

           /* alertDialogBuilder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    */
            AlertDialog alert = alertDialogBuilder.create();
            alert.show();

        }
    }

}
