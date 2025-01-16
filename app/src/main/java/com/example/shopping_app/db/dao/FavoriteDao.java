package com.example.shopping_app.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shopping_app.db.entities.FavoriteEntity;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoriteEntity favoriteItem);

    @Delete
    void delete(FavoriteEntity favoriteItem);

    @Query("DELETE FROM favorite_table")
    void deleteAll();

    @Query("SELECT * FROM favorite_table")
    LiveData<List<FavoriteEntity>> getAllFavorites();

    @Query("SELECT * FROM favorite_table WHERE product_id = :productId")
    LiveData<FavoriteEntity> getFavoriteById(String productId);

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_table WHERE product_id = :productId)")
    LiveData<Boolean> isFavorite(String productId);

    @Query("SELECT COUNT(*) FROM favorite_table")
    LiveData<Integer> getFavoriteCount();
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_table WHERE product_id = :productId)")
    boolean isFavoriteSync(String productId);

    @Query("SELECT * FROM favorite_table WHERE product_id = :productId LIMIT 1")
    FavoriteEntity getFavoriteByIdSync(String productId);
}