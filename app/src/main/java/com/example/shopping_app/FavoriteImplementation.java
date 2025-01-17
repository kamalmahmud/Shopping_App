package com.example.shopping_app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.db.AppDatabase;
import com.example.shopping_app.db.dao.FavoriteDao;
import com.example.shopping_app.db.entities.FavoriteEntity;

public class FavoriteImplementation {
    public static void FavorateStateSwitch(Button favoriteBtn , Context context, ItemListModel item){
        try {
            Log.d("FavoriteClicked", "Starting favorite operation");
            AppDatabase db = AppDatabase.getInstance(context);
            FavoriteDao favoriteDao = db.favoriteDao();


            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    return favoriteDao.isFavoriteSync(item.getDocumentId());
                }

                @Override
                protected void onPostExecute(Boolean isFavorite) {
                    if (!isFavorite) {
                        // Add to favorites
                        FavoriteEntity favItem = new FavoriteEntity();
                        favItem.productId = item.getDocumentId();
                        favItem.name = item.getName();
                        favItem.img = item.getImg();
                        favItem.price = item.getPrice();

                        // Update UI
                        favoriteBtn.setBackground(ContextCompat.getDrawable(context,
                                R.drawable.favorite_icon_filled));

                        // Save in background
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                favoriteDao.insert(favItem);
                                return null;
                            }
                        }.execute();

                    } else {
                        // Remove from favorites
                        new AsyncTask<Void, Void, FavoriteEntity>() {
                            @Override
                            protected FavoriteEntity doInBackground(Void... voids) {
                                return favoriteDao.getFavoriteByIdSync(item.getDocumentId());
                            }

                            @Override
                            protected void onPostExecute(FavoriteEntity favoriteEntity) {
                                if (favoriteEntity != null) {
                                    new AsyncTask<Void, Void, Void>() {
                                        @Override
                                        protected Void doInBackground(Void... voids) {
                                            favoriteDao.delete(favoriteEntity);
                                            return null;
                                        }
                                    }.execute();

                                    favoriteBtn.setBackground(ContextCompat.getDrawable(context,
                                            R.drawable.favorite_icon));
                                }
                            }
                        }.execute();
                    }
                }
            }.execute();

        } catch (Exception e) {
            Log.e("FavoriteClicked", "Error handling favorite: " + e.getMessage());
        }
    }
    public static void FavoriteIconHandler(Button FavoriteBtn,View view,ItemListModel item){
        AppDatabase db = AppDatabase.getInstance(view.getContext());
        FavoriteDao favoriteDao = db.favoriteDao();
        final Boolean[] isFavorite = {false};
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                isFavorite[0] =favoriteDao.isFavoriteSync(item.getDocumentId());
                Log.d("favoriteCheck","item");
                return isFavorite[0];
            }
            @Override
            protected void onPostExecute(Boolean isFavorite) {
                Log.d("favoriteCheck", isFavorite.toString());
                if (isFavorite) {
                    Log.d("favoriteCheck", "fd");
                    FavoriteBtn.setBackground(ContextCompat.getDrawable(view.getContext(),
                            R.drawable.favorite_icon_filled));}}

        }.execute();
    }


}
