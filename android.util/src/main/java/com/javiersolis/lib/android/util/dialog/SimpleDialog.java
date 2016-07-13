package com.javiersolis.lib.android.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SimpleDialog {

	public static void showInfo(Context act, String titulo, String mensaje) {
		new AlertDialog.Builder(act)
				.setTitle(titulo)
				.setMessage(mensaje)
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// continue with delete
							}
						})
				// .setNegativeButton(android.R.string.no, new
				// DialogInterface.OnClickListener() {
				// public void onClick(DialogInterface dialog, int which) {
				// // do nothing
				// }
				// })
				.setIcon(android.R.drawable.ic_dialog_alert).show();
	}
}
