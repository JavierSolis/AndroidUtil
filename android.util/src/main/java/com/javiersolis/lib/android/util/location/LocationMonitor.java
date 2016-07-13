package com.javiersolis.lib.android.util.location;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 1/11/15.
 */
public class LocationMonitor implements
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{

    /*
    ===============================================================================================
    INI - Mostrar LOG
    ===============================================================================================
    */
    private static boolean LOG=false;
    public void enableLog(){this.LOG=true;}
    public void disableLog(){this.LOG=false;}
    public void showLog(String tag, String msg){
        if(this.LOG)
        {
            Log.d(tag, msg);
        }
    }
    /*
    ===============================================================================================
    INI - Mostrar LOG
    ===============================================================================================
    */

    private Activity context;
    private Activity getContext()
    {
        return this.context;
    }

    private ILocationMonitor ilocationMonitor;
    public void setLocationMonitor(ILocationMonitor ilocationMonitor)
    {
           this.ilocationMonitor= ilocationMonitor;
    }


    public LocationMonitor(Activity context)
    {
        this.context=context;
    }

    public Location getCurrentLocation()
    {
        return this.mCurrentLocation;
    }

    /*
    ===============================================================================================
    INI - Metodos que debe ser llamados de quien lo use
    ===============================================================================================
    */

    public void onCreateLocation() {
        this.showLog(TAG, "onCreate ...............................");
        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            //alerrt
            //finish();
            Log.i(TAG,"google play service no avalible");
        }
        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }

    //@Override
    public void onStart() {
        //super.onStart();
        this.showLog(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
    }

    //@Override
    public void onStop() {
        //super.onStop();
        this.showLog(TAG, "onStop fired ..............");
        mGoogleApiClient.disconnect();
        this.showLog(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }

    //override
    public void onPause() {
        stopLocationUpdates();
    }


    //override
    public void onResume() {
        //super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            this.showLog(TAG, "Location update resumed .....................");
        }
    }

    /*
    ===============================================================================================
    FIN - Metodos que debe ser llamados de quien lo use
    ===============================================================================================
    */


    ///////////////////////////////////////

    private FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;

    //////

    /*LISTENER LOCATION*/

    //////

    private static final String TAG = "LocationMonitor";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    private String mLastUpdateTime;

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }







    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, getContext(), 0).show();
            return false;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        this.showLog(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();
    }

    protected void startLocationUpdates() {
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        this.showLog(TAG, "Location update started ..............: ");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.showLog(TAG, "Connection failed: " + connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        this.showLog(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
        if(this.ilocationMonitor!=null)
        {
            this.ilocationMonitor.onLocationUpdate(mCurrentLocation);
        }

    }

    private void updateUI() {
        this.showLog(TAG, "UI update initiated .............");
        if (null != mCurrentLocation) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());

            this.showLog(TAG,"At Time: " + mLastUpdateTime + "\n" +
                    "Latitude: " + lat + "\n" +
                    "Longitude: " + lng + "\n" +
                    "Accuracy: " + mCurrentLocation.getAccuracy() + "\n" +
                    "Provider: " + mCurrentLocation.getProvider());
            /*
            tvLocation.setText("At Time: " + mLastUpdateTime + "\n" +
                    "Latitude: " + lat + "\n" +
                    "Longitude: " + lng + "\n" +
                    "Accuracy: " + mCurrentLocation.getAccuracy() + "\n" +
                    "Provider: " + mCurrentLocation.getProvider());*/
        } else {
            this.showLog(TAG, "location is null ...............");
        }
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        this.showLog(TAG, "Location update stopped .......................");
    }



}
