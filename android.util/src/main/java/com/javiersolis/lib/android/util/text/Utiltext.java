package com.javiersolis.lib.android.util.text;


import android.content.Context;
import android.content.res.Resources;

import com.javiersolis.lib.android.util.math.UtilRound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.Normalizer;

/**
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 */
public class Utiltext {


    public static String normalizar(String title) {
        String normal = Normalizer.normalize(title, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normal.toUpperCase();
    }

    public static String getTextfromURL(String strUrl) throws Exception {
        URL url = new URL(strUrl);

        // Read all the text returned by the server
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String fullContent = "";
        String str;
        while ((str = in.readLine()) != null) {
            // str is one line of text; readLine() strips the newline character(s)
            fullContent += str;
        }
        in.close();

        return fullContent;
    }

    public static String getRoundTwoDecimalFormat(double value) {

        DecimalFormat df = new DecimalFormat("#.00");
        df.setMaximumFractionDigits(2);
        String numberFormat = df.format(UtilRound.round(value, 2));

        return numberFormat;
    }


    public static String getTextFromRaw(Context context, int idRaw) throws IOException {

        Resources res = context.getResources();
        InputStream in_s = res.openRawResource(idRaw);

        byte[] b = new byte[in_s.available()];

        int result = in_s.read(b);

        in_s.close();
        return new String(b);

    }
}
