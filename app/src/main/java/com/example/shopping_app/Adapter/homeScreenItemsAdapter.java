package com.example.shopping_app.Adapter;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.shopping_app.FavoriteImplementation;
import com.example.shopping_app.Interfaces.RecycleViewOnClick;
import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.R;
import com.example.shopping_app.Utils.ImageLoader;
import com.example.shopping_app.db.AppDatabase;
import com.example.shopping_app.db.dao.FavoriteDao;

import java.util.List;

public class homeScreenItemsAdapter extends RecyclerView.Adapter<homeScreenItemsAdapter.ItemListViewholder> {
    private List<ItemListModel> itemListModels;
    private RecycleViewOnClick recycleViewOnClick;

    public homeScreenItemsAdapter(List<ItemListModel> itemListModels, RecycleViewOnClick recycleViewOnClick) {
        this.itemListModels = itemListModels;
        this.recycleViewOnClick = recycleViewOnClick;
    }


    @NonNull
    @Override
    public ItemListViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainscreen_grid_item,parent,false);

        return new ItemListViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewholder holder, int position) {

        holder.bind(itemListModels.get(position),recycleViewOnClick);



    }

    @Override
    public int getItemCount() {
        if(itemListModels==null){
            return 0;
        }else{
        return itemListModels.size();}
    }

    public class ItemListViewholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        ImageView img;
        LinearLayout itemLauout;
        Button FavoriteBtn;
        View ClickablePart;

        public  ItemListViewholder(@NonNull View itemView) {
            super(itemView);
            itemLauout=itemView.findViewById(R.id.homegriditem);
            FavoriteBtn=itemLauout.findViewById(R.id.itemFavoriteBtn);
            ClickablePart=itemLauout.findViewById(R.id.itemClickableview);
            name= ClickablePart.findViewById(R.id.txtitem);
            price=ClickablePart.findViewById(R.id.txtitemprice);
            img=ClickablePart.findViewById(R.id.ItemImg_home);

        }
        public void bind(ItemListModel item,RecycleViewOnClick listener){

            name.setText(item.getName());
            price.setText(item.getPrice().toString());
            FavoriteImplementation.FavoriteIconHandler(FavoriteBtn,itemLauout,item);

            Bitmap itemimg=item.getImg();
            if(itemimg==null){
            ImageLoader.loadImage(item.getImage(), img,item);
            }else{
                img.setImageBitmap(itemimg);
            }
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try{
                    listener.onItemClicked(item);
                    }catch (Exception e){
                        Log.e("clickExc",e.getMessage());
                    }
                }
            });
            FavoriteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAddFavoriteClicked(item,FavoriteBtn);

                }
            });
        }

    }
}
