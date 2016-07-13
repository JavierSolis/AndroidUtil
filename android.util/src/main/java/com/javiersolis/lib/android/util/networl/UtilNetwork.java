package com.javiersolis.lib.android.util.networl;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class UtilNetwork {
	public static boolean isOnline(Activity act) {
		ConnectivityManager cm = (ConnectivityManager) act
				.getSystemService(act.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
}
