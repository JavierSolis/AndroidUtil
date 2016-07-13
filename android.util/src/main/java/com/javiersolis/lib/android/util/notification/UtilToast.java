package com.javiersolis.lib.android.util.notification;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;

public class UtilToast {
	public static void mostrarToastCentroPantalla(Activity act, String msj) {
		Toast t = Toast.makeText(act, msj, Toast.LENGTH_LONG);
		t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
				| Gravity.FILL_HORIZONTAL, 0, 0);
		t.show();
	}
}
