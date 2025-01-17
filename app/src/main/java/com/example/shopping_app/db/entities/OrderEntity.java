package com.example.shopping_app.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class OrderEntity {
    @PrimaryKey(autoGenerate = true)
    public int id = 0;

    @ColumnInfo(name = "order_number")
    public String orderNumber;

    @ColumnInfo(name = "item_count")
    public int itemCount;

    @ColumnInfo(name = "total")
    public float total;

    @ColumnInfo(name = "state")
    public String state;

    @ColumnInfo(name = "created_at")
    public long createdAt;

    public OrderEntity(String orderNumber, int itemCount, float total) {
        this.orderNumber = orderNumber;
        this.itemCount = itemCount;
        this.total = total;
        this.state = "Received";
        this.createdAt = System.currentTimeMillis();
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public int getItemCount() { return itemCount; }
    public void setItemCount(int itemCount) { this.itemCount = itemCount; }

    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}