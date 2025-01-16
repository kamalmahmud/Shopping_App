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

import com.example.shopping_app.R;
import com.google.android.material.button.MaterialButton;

public class CartFragment extends Fragment {

    public CartFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart_with_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        MaterialButton checkoutButton = view.findViewById(R.id.btn_checkout);
        TextView removeAllText = view.findViewById(R.id.tv_remove_all);
        EditText couponEditText = view.findViewById(R.id.et_coupon_code);
        ImageButton applyCouponButton = view.findViewById(R.id.btn_apply_coupon);
        RecyclerView cartItemsRecyclerView = view.findViewById(R.id.rv_cart_items);


        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * Perform checkout action, like opening a checkout screen or processing payment.
                 */
            }
        });

        removeAllText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * Remove all items from the cart.
                 * You might update your ViewModel or data source and refresh the RecyclerView.
                 */
            }
        });

        applyCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String couponCode = couponEditText.getText().toString().trim();
                // Validate and apply coupon code logic
            }
        });

        // Setup the RecyclerView adapter and layout manager if you have actual data
        // cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // cartItemsRecyclerView.setAdapter(new CartItemsAdapter(cartItems));
    }
}
