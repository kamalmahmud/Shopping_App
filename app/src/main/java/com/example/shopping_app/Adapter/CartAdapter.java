package com.example.shopping_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_app.R;
import com.example.shopping_app.db.entities.CartEntity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartEntity> cartItems = new ArrayList<>();
    private CartClickListener listener;

    public interface CartClickListener {
        void onQuantityChanged(CartEntity item, int newQuantity);
        void onItemRemoved(CartEntity item);
    }

    public CartAdapter(CartClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<CartEntity> items) {
        this.cartItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartEntity item = cartItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView nameText, priceText, quantityText;
        private MaterialButton minusButton, plusButton;
        private ImageButton removeButton;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.iv_product);
            nameText = itemView.findViewById(R.id.tv_item_name);
            priceText = itemView.findViewById(R.id.tv_price);
            quantityText = itemView.findViewById(R.id.tv_quantity);
            minusButton = itemView.findViewById(R.id.btn_minus);
            plusButton = itemView.findViewById(R.id.btn_plus);
            removeButton = itemView.findViewById(R.id.btn_remove);
        }

        void bind(CartEntity item) {
            nameText.setText(item.getName());
            priceText.setText(String.format("$%.2f", item.getPrice()));
            quantityText.setText(String.valueOf(item.getQuantity()));
            productImage.setImageBitmap(item.getImgUrl());

            minusButton.setOnClickListener(v -> {
                if (item.getQuantity() > 1) {
                    listener.onQuantityChanged(item, item.getQuantity() - 1);
                }
                if (item.getQuantity() == 1){
                    listener.onItemRemoved(item);
                }
            });

            plusButton.setOnClickListener(v -> {
                listener.onQuantityChanged(item, item.getQuantity() + 1);
            });

            removeButton.setOnClickListener(v -> {
                listener.onItemRemoved(item);
            });
        }
    }
}