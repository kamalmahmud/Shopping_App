package com.example.shopping_app;

import android.database.CursorWindow;

import java.lang.reflect.Field;

public class DatabaseConfig {
    public static void increaseCursorWindowSize() {
        try {
            int cursorWindowSize = 100 * 1024 * 1024; // 100MB
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, cursorWindowSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}