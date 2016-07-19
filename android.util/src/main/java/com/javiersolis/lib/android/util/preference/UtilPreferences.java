package com.javiersolis.lib.android.util.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 */
public class UtilPreferences {


    //util
    private static SharedPreferences getPref(Context context)
    {
        return context.getSharedPreferences(
                context.getPackageName()+"_preference", Context.MODE_PRIVATE);
    }

    public static void putString(Context context, String key, String data)
    {
        SharedPreferences pref = getPref(context);
        pref.edit().putString(key, data).apply();
    }



    public static String getString(Context context, String key )
    {
        SharedPreferences pref=getPref(context);
        return pref.getString(key, null);
    }


    public static void putBoolean(Context context, String key, boolean value)
    {
        SharedPreferences pref = getPref(context);
        pref.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key )
    {
        SharedPreferences pref=getPref(context);
        return pref.getBoolean(key,false);
    }


}
