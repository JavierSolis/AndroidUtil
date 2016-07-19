package com.javiersolis.lib.android.util.gmap;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 * Company @
 */
public class UtilPosition
{
    public static float distanceMeters(double p1Lat, double p1Lon, double p2Lat,double p2Lon)
    {
        Location p1 = new Location("");
        p1.setLatitude(p1Lat);
        p1.setLongitude(p1Lon);

        Location p2 = new Location("");
        p2.setLatitude(p2Lat);
        p2.setLongitude(p2Lon);

        return p1.distanceTo(p2);
    }

    public static float distanceMeters(LatLng p1LatLon, LatLng p2LatLon)
    {
        Location p1 = new Location("");
        p1.setLatitude(p1LatLon.latitude);
        p1.setLongitude(p1LatLon.longitude);

        Location p2 = new Location("");
        p2.setLatitude(p2LatLon.latitude);
        p2.setLongitude(p2LatLon.longitude);

        return p1.distanceTo(p2);
    }
}
