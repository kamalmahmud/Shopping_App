package com.example.shopping_app.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopping_app.Activities.ordersActivity;
import com.example.shopping_app.R;



import android.content.Intent;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class profileFragment extends Fragment {

    private Button btnMyOrders;

    public profileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the button
        btnMyOrders = view.findViewById(R.id.btnMyOrders);

        // Set click listener for My Orders button
        btnMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start OrdersActivity when button is clicked
                Intent intent = new Intent(getActivity(), ordersActivity.class);
                startActivity(intent);
            }
        });
    }
}