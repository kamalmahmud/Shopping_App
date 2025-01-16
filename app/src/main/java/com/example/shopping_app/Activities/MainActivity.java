package com.example.shopping_app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopping_app.Adapter.MainScreenAdapter;
import com.example.shopping_app.DatabaseConfig;
import com.example.shopping_app.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseConfig.increaseCursorWindowSize();
        setContentView(R.layout.activity_main);
        Button cnt = findViewById(R.id.btnContinue);
        cnt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainScreenAdapter.class);
                startActivity(intent);
            }
        });
    }
}