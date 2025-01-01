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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
    // Thread pool for parallel image downloads
    private static final ExecutorService imageLoadingThreadPool = Executors.newFixedThreadPool(3);

    // Handler for updating UI on main thread
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

    // Memory cache for loaded images
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

        // First try to get from cache
        Bitmap cachedBitmap = memoryCache.get(imageUrl);
        if (cachedBitmap != null) {
            imageView.setImageBitmap(cachedBitmap);
            return;
        }

        // Submit image loading task to thread pool
        imageLoadingThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create connection
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();

                    // Download image on background thread
                    InputStream input = connection.getInputStream();
                    Bitmap downloadedBitmap = BitmapFactory.decodeStream(input);

                    if (downloadedBitmap != null) {
                        // Cache the downloaded bitmap
                        memoryCache.put(imageUrl, downloadedBitmap);

                        // Update UI on main thread
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(downloadedBitmap);
                            }
                        });
                    }

                } catch (Exception e) {
                    Log.e("ImageLoader", "Error loading image: " + imageUrl, e);
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(R.drawable.ioadingimg);
                        }
                    });
                }
            }
        });
    }

    // Method to load multiple images in parallel
    public static void loadImages(List<String> imageUrls, List<ImageView> imageViews) {
        if (imageUrls == null || imageViews == null || imageUrls.size() != imageViews.size()) {
            return;
        }

        for (int i = 0; i < imageUrls.size(); i++) {
            final String url = imageUrls.get(i);
            final ImageView imageView = imageViews.get(i);
            loadImage(url, imageView);
        }
    }

    // Method to clear image cache
    public static void clearCache() {
        memoryCache.evictAll();
    }
}