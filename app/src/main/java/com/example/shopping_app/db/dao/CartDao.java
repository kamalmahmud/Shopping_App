package com.example.shopping_app.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shopping_app.db.entities.CartEntity;

import java.util.List;

@Dao
public interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartEntity cartItem);

    @Update
    void update(CartEntity cartItem);

    @Delete
    void delete(CartEntity cartItem);

    @Query("DELETE FROM cart_table")
    void deleteAll();

    @Query("SELECT * FROM cart_table")
    LiveData<List<CartEntity>> getAllCartItems();

    @Query("SELECT COUNT(*) FROM cart_table")
    LiveData<Integer> getCartCount();

    @Query("SELECT * FROM cart_table WHERE product_id = :productId")
    LiveData<CartEntity> getCartItemById(String productId);


    @Query("SELECT SUM(quantity) FROM cart_table")
    LiveData<Integer> getTotalItemCount();

    @Query("SELECT SUM(product_price * quantity) FROM cart_table")
    LiveData<Float> getTotalPrice();

    @Query("UPDATE cart_table SET quantity = :quantity WHERE product_id = :productId")
    void updateQuantity(String productId, int quantity);
}