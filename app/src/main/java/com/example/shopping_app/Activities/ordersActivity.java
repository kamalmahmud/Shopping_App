package com.example.shopping_app.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_app.Adapter.OrderAdapter;
import com.example.shopping_app.R;
import com.example.shopping_app.db.AppDatabase;
import com.example.shopping_app.db.dao.OrderDao;
import com.example.shopping_app.db.entities.OrderEntity;

import java.util.List;

public class ordersActivity extends AppCompatActivity {

    ImageButton imageButton;
    private RecyclerView recyclerOrders;
    private View layoutEmpty;
    private OrderAdapter adapter;
    private OrderDao orderDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders);

        orderDao = AppDatabase.getInstance(this).OrderDao();

        recyclerOrders = findViewById(R.id.recyclerOrders);
        layoutEmpty = findViewById(R.id.layoutEmpty);
        imageButton =(ImageButton) findViewById(R.id.orderBtnBack);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        adapter = new OrderAdapter();
        recyclerOrders.setAdapter(adapter);
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<OrderEntity> orders = orderDao.getAllOrders();
                    runOnUiThread(() -> {
                        if (orders.isEmpty()) {
                            recyclerOrders.setVisibility(View.GONE);
                            layoutEmpty.setVisibility(View.VISIBLE);
                        } else {
                            recyclerOrders.setVisibility(View.VISIBLE);
                            layoutEmpty.setVisibility(View.GONE);
                            adapter.setOrders(orders);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}