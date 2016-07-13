package com.javiersolis.lib.android.util.device;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 26/12/15.
 */
public class UtilDeviceInfo {

    //discussion: http://stackoverflow.com/questions/9279111/determine-if-the-device-is-a-smartphone-or-tablet

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }



    //http://stackoverflow.com/questions/1995439/get-android-phone-model-programmatically

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


    //http://stackoverflow.com/questions/1972381/how-to-get-the-devices-imei-esn-programmatically-in-android
    public static boolean imeiAvailable(Context context)
    {
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if( tm == null)
        {
            return false;
        }

        String identifier = tm.getDeviceId();
        if (TextUtils.isEmpty(identifier))
        {
            return false;
        }

        return true;
    }

    public static final String getImei(Context context)
    {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static final String getAndroidId(Context context)
    {

        String identifier = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        return identifier;
    }
}
