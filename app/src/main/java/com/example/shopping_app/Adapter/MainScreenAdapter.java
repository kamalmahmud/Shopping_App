package com.example.shopping_app.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.shopping_app.CartFragment;
import com.example.shopping_app.FavoritesFragment;
import com.example.shopping_app.Fragment.HomePageFragment;
import com.example.shopping_app.Interfaces.RecycleViewOnClick;
import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.ProductDetailActivity;
import com.example.shopping_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreenAdapter extends AppCompatActivity implements RecycleViewOnClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_screen_adapter);

        BottomNavigationView btmNav=findViewById(R.id.bottomNavigationView);
        FragmentManager fm=getSupportFragmentManager();

        if (savedInstanceState == null) {// Important to avoid recreating fragment after orientation changes
            fm.beginTransaction()
                    .replace(R.id.framelayout, new HomePageFragment()).addToBackStack("HomeScreen")
                    .commit();
         }
        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment=null;
                if(item.getItemId() == R.id.cart_screen){
                    selectedFragment=new CartFragment();
                } else if (item.getItemId() ==R.id.favorite_screen) {
                    selectedFragment=new FavoritesFragment();
                } else if (item.getItemId()==R.id.home_screen) {
                    selectedFragment=new HomePageFragment();

                }
                if(selectedFragment!=null) {

                    fm.beginTransaction().replace(R.id.framelayout, selectedFragment, null).addToBackStack(selectedFragment.getTag()).commit();
                }


                return true;
            }
        });
    }




    @Override
    public void onItemClicked(ItemListModel item) {

    }
}