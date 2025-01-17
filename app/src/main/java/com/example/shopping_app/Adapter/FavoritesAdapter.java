package com.example.shopping_app.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_app.FavoriteImplementation;
import com.example.shopping_app.NotUsedYet.FavoriteItem;
import com.example.shopping_app.R;
import com.example.shopping_app.db.AppDatabase;
import com.example.shopping_app.db.dao.FavoriteDao;
import com.example.shopping_app.db.entities.FavoriteEntity;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    private List<FavoriteEntity> items;
    LifecycleOwner lifecycleOwner;

    public FavoritesAdapter(List<FavoriteEntity> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_item, parent, false);
        Log.d("FavoriteViewHolder","gf");

        return new FavoriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        FavoriteEntity item = items.get(position);
        AppDatabase db = AppDatabase.getInstance(context);
        FavoriteDao favoriteDao = db.favoriteDao();
        holder.itemName.setText(item.name);
        holder.itemPrice.setText(item.price.toString());
        holder.itemImage.setImageBitmap(item.img);
        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        favoriteDao.delete(item);
                        return null;
                    }
                }.execute();
            }
        });

        // Handle favorite button if needed (e.g., remove from favorites)
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView itemPrice;
        Button btnFavorite;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.iv_item_image);
            itemName = itemView.findViewById(R.id.tv_item_name);
            itemPrice = itemView.findViewById(R.id.tv_item_price);
            btnFavorite = itemView.findViewById(R.id.btn_favorite);
            btnFavorite.setBackground(ContextCompat.getDrawable(itemView.getContext(),
                    R.drawable.favorite_icon_filled));
        }

    }
}
