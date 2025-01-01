package com.example.shopping_app.db.entities;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_table")
public class CartEntity {
    @PrimaryKey(autoGenerate = true)
    public int id = 0;

    @ColumnInfo(name = "product_id")
    public String productId;

    @ColumnInfo(name = "product_name")
    public String name;

    @ColumnInfo(name = "product_price")
    public float price;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "imgUrl")
    public Bitmap imgUrl;
}
