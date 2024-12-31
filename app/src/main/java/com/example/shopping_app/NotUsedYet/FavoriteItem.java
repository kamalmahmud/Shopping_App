package com.example.shopping_app.NotUsedYet;

public class FavoriteItem {
    private String name;
    private String price;
    private int imageRes; // For simplicity, using a drawable resource. In a real scenario, use URLs.

    public FavoriteItem(String name, String price, int imageRes) {
        this.name = name;
        this.price = price;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageRes() {
        return imageRes;
    }
}

