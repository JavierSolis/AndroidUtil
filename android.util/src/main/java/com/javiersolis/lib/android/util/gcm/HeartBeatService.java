package com.javiersolis.lib.android.util.gcm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 10/12/15.
 */
public class HeartBeatService extends Service {

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getApplicationContext().sendBroadcast(new Intent("com.google.android.intent.action.GTALK_HEARTBEAT"));
        getApplicationContext().sendBroadcast(new Intent("com.google.android.intent.action.MCS_HEARTBEAT"));
        return super.onStartCommand(intent, flags, startId);
    }

}

/*EXAMPLE EN MANIFST*/
/*


 <!-- HEARTBEAT SERVICE -->
    <service
        android:name=".HeartBeatService"
        android:exported="false" >
        <intent-filter>
            <action android:name="com.joaopedronardari.androidheartbeatfixer.HEART_BEAT_SERVICE" />
            <category android:name="android.intent.category.DEFAULT" />
        </intent-filter>
    </service>

*/