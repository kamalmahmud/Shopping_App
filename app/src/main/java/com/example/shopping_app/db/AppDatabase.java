package com.example.shopping_app.db;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.shopping_app.db.dao.CartDao;
import com.example.shopping_app.db.dao.FavoriteDao;
import com.example.shopping_app.db.entities.CartEntity;
import com.example.shopping_app.db.entities.FavoriteEntity;

import java.io.ByteArrayOutputStream;
//   ما كتب هاد الكود ai
@Database(entities = {CartEntity.class, FavoriteEntity.class}, version = 1)
@TypeConverters({AppDatabase.Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract CartDao cartDao();
    public abstract FavoriteDao favoriteDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "shopping_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    // Inner Converters class for Bitmap conversion
    public static class Converters {
        @TypeConverter
        public static byte[] fromBitmap(Bitmap bitmap) {
            if (bitmap == null) return null;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        }

        @TypeConverter
        public static Bitmap toBitmap(byte[] bytes) {
            if (bytes == null) return null;
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
    }
}

// this is how to call the appDataBase
//         AppDatabase db = AppDatabase.getInstance(context);
//        // Access Cart DAO
//        CartDao cartDao = db.cartDao();
//        // Access Favorite DAO
//        FavoriteDao favoriteDao = db.favoriteDao();