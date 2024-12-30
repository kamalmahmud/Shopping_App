package com.example.shopping_app.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping_app.Interfaces.RecycleViewOnClick;
import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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

        public  ItemListViewholder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.txtitem);
            price=itemView.findViewById(R.id.txtitemprice);
            img=itemView.findViewById(R.id.ItemImg_home);



        }
        public void bind(ItemListModel item,RecycleViewOnClick listener){
            Log.d("dfdfdf","dff");
            name.setText(item.getName());
            price.setText(item.getPrice());

            img.setImageBitmap(item.getImg());
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
        }

    }
}
