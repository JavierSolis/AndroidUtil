package com.javiersolis.lib.android.util.image;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 9/12/15.
 */
public class UtilViewImage
{
    public static void showImageFromDrawable(int idDrawable,Context context)
    {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), idDrawable);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + "test.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Uri path = Uri.fromFile(f);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(path, "image/*");
        context.startActivity(intent);
    }
}
