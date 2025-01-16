package com.example.shopping_app.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopping_app.Adapter.ImageSliderAdapter;
import com.example.shopping_app.FavoriteImplementation;
import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.R;
import com.example.shopping_app.ViewModel.ItemListViewModel;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    ViewPager2 vp;
    TextView title;
    ImageButton BackBtn;
    Button FavBtn;
    TextView price;
    TextView description;
    Spinner size_spinner;
    Spinner color_spinner;
    Button increaseQuantityBtn;
    Button decreaseQuantityBtn;


    Integer Quantity =1;
    TextView QuantityTxt;
    ItemListViewModel itemListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        BackBtn= (ImageButton) findViewById(R.id.back_button);
        FavBtn=(Button) findViewById(R.id.heart_button);
        increaseQuantityBtn=(Button)findViewById(R.id.quantity_increase);
        decreaseQuantityBtn=(Button)findViewById(R.id.quantity_decrease);
        QuantityTxt=findViewById(R.id.quantity_text);

        vp = findViewById(R.id.image_slider);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        size_spinner = findViewById(R.id.size_spinner);
        color_spinner = findViewById(R.id.color_spinner);



            itemListViewModel=new ItemListViewModel();
            ItemListModel item = itemListViewModel.getItemById(getIntent().getStringExtra("Item ID"));
            title.setText(item.getName());
            price.setText(item.getPrice().toString());
            description.setText(item.getDescription());
            BackBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        FavoriteImplementation.FavoriteIconHandler(FavBtn,FavBtn,item);
        ArrayList<Bitmap> images = new ArrayList<>(); //Adding the same image of the item five times
        for (int i = 0; i <5 ; i++) {
            images.add(item.getImg());
        }
        ImageSliderAdapter adapter = new ImageSliderAdapter(images);
        vp.setAdapter(adapter);


        //Quantity adding,removing implementation
//        try {
            decreaseQuantityBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Quantity > 1) {
                        Quantity -= 1;
                        QuantityTxt.setText(Quantity.toString());

                    }

                }
            });
            increaseQuantityBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Quantity += 1;
                    QuantityTxt.setText(Quantity.toString());
                }
            });
        Context context=this;
                FavBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FavoriteImplementation.FavorateStateSwitch(FavBtn,context,item);
                }
            });




    }
}