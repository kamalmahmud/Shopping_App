package com.example.shopping_app.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.R;

import java.util.List;

public class homeScreenItemsAdapter extends RecyclerView.Adapter<homeScreenItemsAdapter.ItemListViewholder> {
    private List<ItemListModel> itemListModels;
    public void setItemListModels(List<ItemListModel> itemListModels) {
        this.itemListModels = itemListModels;
        Log.e("positemholer", "11");
    }
    @NonNull
    @Override
    public ItemListViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("positemview", "11");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainscreen_grid_item,parent,false);

        return new ItemListViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewholder holder, int position) {
        ItemListModel model=itemListModels.get(position);
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        Log.e("positem", "11");
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
        public ItemListViewholder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.txtitem);
            price=itemView.findViewById(R.id.txtitemprice);

        }
    }
}
