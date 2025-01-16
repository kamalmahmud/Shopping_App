package com.example.shopping_app.db.dao;

import androidx.room.*;
import com.example.shopping_app.db.entities.OrderEntity;
import java.util.List;

@Dao
public interface OrderDao {
    @Query("SELECT * FROM orders ORDER BY created_at DESC")
    List<OrderEntity> getAllOrders();

    @Query("SELECT * FROM orders WHERE id = :id")
    OrderEntity getOrderById(int id);

    @Insert
    void insert(OrderEntity order);

    @Update
    void update(OrderEntity order);

    @Delete
    void delete(OrderEntity order);

    @Query("SELECT COUNT(*) FROM orders")
    int getOrderCount();
}