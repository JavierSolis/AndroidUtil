package com.javiersolis.lib.android.util.connection;

import android.content.Context;


import com.javiersolis.lib.android.util.dialog.UtilDialogAlert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 4/12/15.
 */
public class UtilConnectionDialogValid {

    private Context context;
    private String host;

    private String msgHost;
    private String msgConnect;

    public UtilConnectionDialogValid(Context context, String host, int idMessageFailHost, int idMessageFailConnection)
    {
        this.context=context;
        this.host="google.com";

        try {
            URL aURL = new URL(host);
            this.host=aURL.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.msgConnect=context.getString(idMessageFailConnection);
        this.msgHost=context.getString(idMessageFailHost);
    }

    public boolean isConnected()
    {
        if(!UtilConnection.isOnline(this.context))
        {
            UtilDialogAlert.showAlert(this.context, "Conexión", msgConnect);
            return false;
        }

        /*
        if(!UtilConnection.isOnlinePingHost(this.context, this.host))
        {
            UtilDialog.showAlert(this.context,"Conexión",msgHost);
            return false;
        }
        */
        return true;
    }
}
