package com.javiersolis.lib.android.util.location;

import android.location.Address;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 16/11/15.
 */
public interface IGetAdressName {
    public void onResultAddressName(double latitude, double longitude, Address direccion);
}
