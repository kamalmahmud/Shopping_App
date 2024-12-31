package com.example.shopping_app.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.R;
import com.example.shopping_app.ViewModel.ItemListViewModel;

public class ProductActivity extends AppCompatActivity {
    ViewPager2 vp;
    TextView title;
    TextView price;
    TextView description;
    Spinner size_spinner;
    Spinner color_spinner;
    ItemListViewModel itemListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        vp = findViewById(R.id.image_slider);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        size_spinner = findViewById(R.id.size_spinner);
        color_spinner = findViewById(R.id.color_spinner);
        try {

            itemListViewModel=new ItemListViewModel();
            ItemListModel item = itemListViewModel.getItemById(getIntent().getStringExtra("Item ID"));
            title.setText(item.getName());
            price.setText(item.getPrice());
            description.setText(item.getDescription());

        }catch (Exception e){
            Log.e("ProductDetails init",e.getMessage());
        }
        //Images not implemented yet the item has one image item.getimg();
//        ArrayList<Bitmap> images = item.getParcelableArrayList("images");
//
//        ImageSliderAdapter adapter = new ImageSliderAdapter(images);
//        vp.setAdapter(adapter);



    }
}