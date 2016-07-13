package com.javiersolis.lib.android.util.image;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 22/11/15.
 */
public class UtilDialogPickImg
implements DialogInterface.OnClickListener{

    private Activity context;
    private CharSequence[] items;
    private String title;

    public final static int DIALOG_PICK_REQUEST_CAMERA=9881;
    public final static int DIALOG_PICK_SELECT_FILE=982;

    public UtilDialogPickImg(Activity context)
    {
        this.context= context;
        CharSequence[] items = { "Tomar Foto", "Escoger de la galería", "Cancelar" };
        this.items = items;
        this.title="Elegir foto desde:";
    }
    public void showSelectImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("¿De donde obtendrá la foto?");
        builder.setItems(items, this);
        builder.show();
    }



    @Override
    public void onClick(DialogInterface dialog, int item) {
        if (item==0)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                // Create the File where the photo should go
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                context.startActivityForResult(intent, DIALOG_PICK_REQUEST_CAMERA);
            }

        }
        else if (item==1)
        {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");

            context.startActivityForResult
                    (
                            Intent.createChooser(intent, "Escoger Foto"),
                            DIALOG_PICK_SELECT_FILE
                    );
        }
        else
        {
            dialog.dismiss();
        }
    }

    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStorageDirectory();
        File image = new File(

                storageDir      /* directory */
                ,imageFileName+ /* prefix */
                ".jpg"     /* suffix */
        );


        if(image.exists())
        {
            image.delete();
        }
        image.createNewFile();

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getPath();
        return image;
    }


    public String onActivityResult(int requestCode, int resultCode, Intent data) throws FileNotFoundException, IOException,Exception {

        String pathResultFile=null;
        if (resultCode == this.context.RESULT_OK) {
            int sizeRequired=400;
            String nameNewFile= System.currentTimeMillis()+".jpg";



            if (requestCode == DIALOG_PICK_REQUEST_CAMERA) {
                try {
                    //data solo pbtiene lthumbail
                    //Bitmap thumbnail = (Bitmap) data.getExtras().get("data");


                    //ANTES
                    // ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    // thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                    //String pathNewFile =this.bitmapToFile(thumbnail,nameNewFile);
                    Bitmap tmp = this.fileToBitmap(this.mCurrentPhotoPath,sizeRequired);
                    pathResultFile = this.bitmapToFile(tmp,nameNewFile);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    throw e;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }


            } else if (requestCode == DIALOG_PICK_SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = { MediaStore.MediaColumns.DATA };
                CursorLoader cursorLoader = new CursorLoader(this.context,selectedImageUri, projection, null, null,
                        null);
                Cursor cursor =cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);


                // se combierete  bitmap
                Bitmap newbm= this.fileToBitmap(selectedImagePath,sizeRequired);
                pathResultFile = this.bitmapToFile(newbm,nameNewFile);

            }
        }
        return pathResultFile;
    }

    public Bitmap fileToBitmap(String pathFileOrigin, int sizeRequired)
    {
        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathFileOrigin, options);
        final int REQUIRED_SIZE = sizeRequired;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(pathFileOrigin, options);


        return bm;
    }
    //UTIL
    public String bitmapToFile(Bitmap bm, String nameFile) throws Exception
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, bytes);

        //File destination = new File(pathNewFile);
        File destination = new File(Environment.getExternalStorageDirectory(),
               nameFile );

        FileOutputStream fo;
        try {
            if(destination.exists())
            {
                destination.delete();
            }
            destination.createNewFile();

            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.flush();
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        String pathResultFile = destination.getPath();


        return pathResultFile;
    }
}




/*ORIGINAL
*
* protected String onActivityResult(int requestCode, int resultCode, Intent data) {

        String pathResultFile=null;
        if (resultCode == this.context.RESULT_OK) {
            if (requestCode == DIALOG_PICK_REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ivImage.setImageBitmap(thumbnail);


            } else if (requestCode == DIALOG_PICK_SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = { MediaStore.MediaColumns.DATA };
                CursorLoader cursorLoader = new CursorLoader(this.context,selectedImageUri, projection, null, null,
                        null);
                Cursor cursor =cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);

                ivImage.setImageBitmap(bm);
            }
        }

        return pathResultFile;
    }

    */