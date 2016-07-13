package com.javiersolis.lib.android.util.location;

import android.location.Location;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 1/11/15.
 */
public interface ILocationMonitor {
    public void onLocationUpdate(Location location);
}
