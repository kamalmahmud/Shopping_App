package com.example.shopping_app.Adapter;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.shopping_app.HomePageFragment;
import com.example.shopping_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreenAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_screen_adapter);
        BottomNavigationView btmNav=findViewById(R.id.bottomNavigationView);
        FragmentManager fragmentmanager=getSupportFragmentManager();
        fragmentmanager.beginTransaction().replace(R.id.framelayout, HomePageFragment.class,null).commit();
        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment=null;
                if(item.getItemId() == R.id.cart_screen){
                    selectedFragment=new Fragment(R.layout.fragment_cart);
                } else if (item.getItemId() ==R.id.favorite_screen) {
                    selectedFragment=new Fragment(R.layout.fragment_favorites);
                } else if (item.getItemId()==R.id.home_screen) {
                    selectedFragment=new Fragment(R.layout.fragment_home_screen);

                }
                if(selectedFragment!=null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment, null).commit();
                }
                return true;
            }
        });
    }
}