package com.javiersolis.lib.android.util.email;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * @author Javier Solis Flores
 *
 */
public class UtilEmail {
	public static void enviarCorreo(Activity activity, String correoDestino,
									String asunto, String mensaje) {
		Intent send = new Intent(Intent.ACTION_SENDTO);
		String uriText = "mailto:" + Uri.encode(correoDestino) + "?subject="
				+ Uri.encode(asunto) + "&body=" + Uri.encode(mensaje);
		Uri uri = Uri.parse(uriText);

		send.setData(uri);
		activity.startActivity(Intent.createChooser(send, "Enviar correo..."));
	}
}
