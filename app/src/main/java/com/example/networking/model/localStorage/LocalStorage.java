package com.example.networking.model.localStorage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;
import static android.graphics.BitmapFactory.decodeStream;

public class LocalStorage {
    private static final String IMAGES = "/images";
    private static final String AVATARS = "/.avatars";

    public static Bitmap GetImage(Context context, int userId) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

        bitmapOptions.inSampleSize = 2;
        bitmapOptions.inDither = false;
        bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;

        Bitmap imageBts;
        try {
            imageBts = decodeStream(new FileInputStream(new File(
                    getFileName(context, userId))), null, bitmapOptions);
        } catch (IOException e) {
            e.printStackTrace();
            imageBts =  null;
        }
        return imageBts;
    }

    public static Target ImageSavePicasso(final Context context, final int userId) {
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, final Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                            Log.i(TAG, "The SD Card is not Mounted");
                        } else {
                            File file = new File(getFileName(context, userId));
                            try {
                                FileOutputStream ostream = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                                ostream.close();

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(TAG, e.getMessage());
                            }
                        }

                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
    }

    private static String getFileName(Context context, int userId) {
        return Environment.getDataDirectory().getAbsolutePath() + IMAGES + AVATARS + String.valueOf(userId);
    }
}
