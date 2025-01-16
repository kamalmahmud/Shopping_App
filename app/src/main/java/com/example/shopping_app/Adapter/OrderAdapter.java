package com.example.shopping_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_app.R;
import com.example.shopping_app.db.entities.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<OrderEntity> orders = new ArrayList<>();

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderEntity order = orders.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView orderNumberText, itemCountText, totalText, stateText;

        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumberText = itemView.findViewById(R.id.tv_order_number);
            itemCountText = itemView.findViewById(R.id.tv_item_count);
            totalText = itemView.findViewById(R.id.tv_total);
            stateText = itemView.findViewById(R.id.tv_state);
        }

        void bind(OrderEntity order) {
            orderNumberText.setText("Order #" + order.getOrderNumber());
            itemCountText.setText(order.getItemCount() + " Items");
            totalText.setText(String.format("$%.2f", order.getTotal()));
            stateText.setText(order.getState());
        }
    }
}