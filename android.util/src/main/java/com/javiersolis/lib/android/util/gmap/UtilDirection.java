package com.javiersolis.lib.android.util.gmap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 */
public class UtilDirection
{
    public static void launchDirection(Context context,double lat, double lon)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr="+lat+","+lon));
        context.startActivity(intent);
    }

    public static void launchDirection(Context context)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
        context.startActivity(intent);
    }
}

