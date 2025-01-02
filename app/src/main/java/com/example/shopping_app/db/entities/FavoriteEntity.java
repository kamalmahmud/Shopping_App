package com.example.shopping_app.db.entities;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.shopping_app.Model.ItemListModel;

@Entity(tableName = "favorite_table")

public class FavoriteEntity {
    @PrimaryKey(autoGenerate = true)
    public int id = 0;

    @ColumnInfo(name = "product_id")
    public String productId;

    @ColumnInfo(name = "product_name")
    public String name;

    @ColumnInfo(name = "product_price")
    public Float price;

    @ColumnInfo(name = "img")
    public Bitmap img;


}
