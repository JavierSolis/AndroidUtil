package com.javiersolis.lib.android.util.sms;

import android.telephony.SmsManager;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 2/01/16.
 */
public class UtilSendSMS {
    public static void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
