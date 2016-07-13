package com.javiersolis.lib.android.util.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 9/12/15.
 */
public class UtilAppInfo
{
    public static int getAppVersionCode(Context context)
    {
        try
        {
            PackageInfo pInfo = null;
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = pInfo.versionName;
            int verCode = pInfo.versionCode;
            return verCode;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

        return -1;
    }

    public static String getPackageName(Context context)
    {
        try
        {
            return context.getPackageName();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
