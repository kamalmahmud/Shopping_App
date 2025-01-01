package com.example.shopping_app.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.shopping_app.R;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
    private static final ExecutorService imageLoadingThreadPool = Executors.newFixedThreadPool(3);
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final LruCache<String, Bitmap> memoryCache;

    static {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static void loadImage(String imageUrl, ImageView imageView) {
        if (imageUrl == null || imageView == null) return;

        Bitmap cachedBitmap = memoryCache.get(imageUrl);
        if (cachedBitmap != null) {
            imageView.setImageBitmap(cachedBitmap);
            return;
        }

        imageLoadingThreadPool.execute(() -> {
            try {
                URL url = new URL(imageUrl);
                InputStream in = url.openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                in.close();

                if (bitmap != null) {
                    memoryCache.put(imageUrl, bitmap);
                    mainHandler.post(() -> imageView.setImageBitmap(bitmap));
                }
            } catch (Exception e) {
                Log.e("ImageLoader", "Error loading image: " + imageUrl, e);
                mainHandler.post(() -> imageView.setImageResource(R.drawable.ioadingimg));
            }
        });
    }
}