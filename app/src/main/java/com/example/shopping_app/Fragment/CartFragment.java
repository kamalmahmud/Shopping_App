package com.example.shopping_app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopping_app.Adapter.CartAdapter;
import com.example.shopping_app.R;
import com.example.shopping_app.db.AppDatabase;
import com.example.shopping_app.db.dao.CartDao;
import com.example.shopping_app.db.entities.CartEntity;

public class CartFragment extends Fragment implements CartAdapter.CartClickListener {
    private CartAdapter adapter;
    private CartDao cartDao;
    private TextView subtotalText, shippingText, taxText, totalText;
    private static final float SHIPPING_RATE = 8.00f;
    private static final float TAX_RATE = 0.08f;
    private View cartContentLayout;
    private View emptyCartLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartDao = AppDatabase.getInstance(requireContext()).cartDao();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emptyCartLayout = view.findViewById(R.id.empty_cart_layout);
        cartContentLayout = view.findViewById(R.id.cart_content_layout);


        initializeViews(view);
        setupRecyclerView(view);
        setupClickListeners(view);

        cartDao.getCartCount().observe(getViewLifecycleOwner(), count -> {
            if (count == 0) {
                cartContentLayout.setVisibility(View.GONE);
                emptyCartLayout.setVisibility(View.VISIBLE);
            } else {
                cartContentLayout.setVisibility(View.VISIBLE);
                emptyCartLayout.setVisibility(View.GONE);
            }
        });

        observeCartData();
    }

    // initializing the views
    private void initializeViews(View view) {
        subtotalText = view.findViewById(R.id.tv_subtotal_amount);
        shippingText = view.findViewById(R.id.tv_shipping_cost_amount);
        taxText = view.findViewById(R.id.tv_tax_amount);
        totalText = view.findViewById(R.id.tv_total_amount);
        shippingText.setText(String.format("$%.2f", SHIPPING_RATE));
    }

    // siting up the recycler view using the cartAdapter
    private void setupRecyclerView(View view) {
        RecyclerView cartItemsRecyclerView = view.findViewById(R.id.rv_cart_items);
        adapter = new CartAdapter(this);
        cartItemsRecyclerView.setAdapter(adapter);
    }

    // making the two buttons works (remove all and checkout)
    private void setupClickListeners(View view) {
        view.findViewById(R.id.tv_remove_all).setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cartDao.deleteAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });        view.findViewById(R.id.btn_checkout).setOnClickListener(v -> handleCheckout());
    }

    private void observeCartData() {
        // it will set the items in the recycleView
        cartDao.getAllCartItems().observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
        });

        // updating the last section which includes total price ,tax and shipping
        cartDao.getTotalPrice().observe(getViewLifecycleOwner(), subtotal -> {
            if (subtotal != null) {
                subtotalText.setText(String.format("$%.2f", subtotal));
                float tax = subtotal * TAX_RATE;
                taxText.setText(String.format("$%.2f", tax));
                totalText.setText(String.format("$%.2f", subtotal + tax + SHIPPING_RATE));
            } else {
                subtotalText.setText("$0.00");
                taxText.setText("$0.00");
                totalText.setText("$0.00");
            }
        });
    }


    // it is used in cartAdapter to update the quantity by clicking the buttons
    @Override
    public void onQuantityChanged(CartEntity item, int newQuantity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cartDao.updateQuantity(item.getProductId(), newQuantity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();    }

    // removing the itme
    @Override
    public void onItemRemoved(CartEntity item) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cartDao.delete(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // it should be implemented to switch to anther page and remove all the cart items in put them
    // the orders list
    private void handleCheckout() {
        // Navigation to checkout implementation
    }
}