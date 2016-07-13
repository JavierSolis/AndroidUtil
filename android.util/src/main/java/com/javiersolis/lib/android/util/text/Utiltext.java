package com.javiersolis.lib.android.util.text;


import com.javiersolis.lib.android.util.math.UtilRound;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.Normalizer;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 3/11/15.
 */
public class Utiltext {


    public static String normalizar(String title)
    {
        String normal= Normalizer.normalize(title, Normalizer.Form.NFD) .replaceAll("\\p{InCombiningDiacriticalMarks}+", "") ;
        return normal.toUpperCase();
    }

    public static String getTextfromURL(String strUrl) throws Exception {
        URL url = new URL(strUrl);

        // Read all the text returned by the server
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String fullContent="";
        String str;
        while ((str = in.readLine()) != null) {
            // str is one line of text; readLine() strips the newline character(s)
            fullContent+=str;
        }
        in.close();

        return fullContent;
    }

    public static String getRoundTwoDecimalFormat(double value)
    {

        DecimalFormat df = new DecimalFormat("#.00");
        df.setMaximumFractionDigits(2);
        String numberFormat = df.format(UtilRound.round(value, 2));

        return numberFormat;
    }
}
