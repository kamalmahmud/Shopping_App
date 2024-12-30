package com.example.shopping_app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopping_app.Adapter.ImageSliderAdapter;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    ViewPager2 vp = findViewById(R.id.image_slider);
    TextView title = findViewById(R.id.title);
    TextView price = findViewById(R.id.price);
    TextView description = findViewById(R.id.description);
    Spinner size_spinner = findViewById(R.id.size_spinner);
    Spinner color_spinner = findViewById(R.id.color_spinner);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Bundle bundle = getIntent().getExtras();
        title.setText(bundle.getString("title"));
        price.setText(bundle.getString("price"));
        description.setText(bundle.getString("description"));
        ArrayList<Bitmap> images = bundle.getParcelableArrayList("images");

        ImageSliderAdapter adapter = new ImageSliderAdapter(images);
        vp.setAdapter(adapter);



    }
}