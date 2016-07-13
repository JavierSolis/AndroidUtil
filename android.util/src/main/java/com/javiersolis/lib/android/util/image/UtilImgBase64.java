package com.javiersolis.lib.android.util.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 22/11/15.
 */
public class UtilImgBase64 {
    public static String encode64(String pathFile)
    {
        BitmapFactory.Options options = null;
        options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        Bitmap bitmap = BitmapFactory.decodeFile(pathFile,options);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50,stream);

        byte[] bytes_array = stream.toByteArray();

        String encodeString = Base64.encodeToString(bytes_array,0);

        return  encodeString;

    }
}
