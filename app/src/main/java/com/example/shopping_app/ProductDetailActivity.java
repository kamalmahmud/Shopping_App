package com.example.shopping_app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {
    private Spinner sizeSpinner;
    private Spinner colorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // Initialize Spinners
        sizeSpinner = findViewById(R.id.size_spinner);
        colorSpinner = findViewById(R.id.color_spinner);

        // Setup Size Spinner
        setupSizeSpinner();

        // Setup Color Spinner
        setupColorSpinner();
    }

    private void setupSizeSpinner() {
        // Create array of sizes
        String[] sizes = {"Select Size", "XS", "S", "M", "L", "XL", "XXL"};

        // Create adapter
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                sizes
        );

        // Set dropdown view resource
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter to spinner
        sizeSpinner.setAdapter(sizeAdapter);

        // Set default selection
        sizeSpinner.setSelection(0);

        // Set item selection listener
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Skip the first item (placeholder)
                if (position > 0) {
                    String selectedSize = parent.getItemAtPosition(position).toString();
                    // Do something with the selected size
                    // For example: Toast.makeText(ProductDetailActivity.this, "Selected Size: " + selectedSize, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    private void setupColorSpinner() {
        // Create array of colors
        String[] colors = {"Select Color", "Black", "Navy", "Gray", "Khaki"};

        // Create adapter
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                colors
        );

        // Set dropdown view resource
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter to spinner
        colorSpinner.setAdapter(colorAdapter);

        // Set default selection
        colorSpinner.setSelection(0);

        // Set item selection listener
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Skip the first item (placeholder)
                if (position > 0) {
                    String selectedColor = parent.getItemAtPosition(position).toString();
                    // Do something with the selected color
                    // For example: Toast.makeText(ProductDetailActivity.this, "Selected Color: " + selectedColor, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }
}
