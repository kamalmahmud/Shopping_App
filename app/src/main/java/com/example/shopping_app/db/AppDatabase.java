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
import com.example.shopping_app.db.dao.OrderDao;
import com.example.shopping_app.db.entities.CartEntity;
import com.example.shopping_app.db.entities.FavoriteEntity;
import com.example.shopping_app.db.entities.OrderEntity;

import java.io.ByteArrayOutputStream;

@Database(entities = {CartEntity.class, FavoriteEntity.class, OrderEntity.class}, version = 2) // Changed version from 1 to 2
@TypeConverters({AppDatabase.Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract CartDao cartDao();
    public abstract FavoriteDao favoriteDao();
    public abstract OrderDao OrderDao();  // Added OrderDao

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "shopping_database")
                    .fallbackToDestructiveMigration()  // This will delete the old database and create a new one
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