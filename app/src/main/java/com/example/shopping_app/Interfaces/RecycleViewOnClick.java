package com.example.shopping_app.Interfaces;

import android.widget.Button;

import com.example.shopping_app.Model.ItemListModel;

public interface RecycleViewOnClick {
    void onItemClicked(ItemListModel item);
    void onAddFavoriteClicked(ItemListModel item, Button favoriteBtn);
}
