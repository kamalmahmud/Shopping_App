package com.example.shopping_app.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopping_app.Adapter.ImageSliderAdapter;
import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.R;
import com.example.shopping_app.ViewModel.ItemListViewModel;
import com.example.shopping_app.db.AppDatabase;
import com.example.shopping_app.db.dao.CartDao;
import com.example.shopping_app.db.entities.CartEntity;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    ViewPager2 vp;
    TextView title;
    ImageButton BackBtn;
    TextView price;
    TextView description;
    Spinner size_spinner;
    Spinner color_spinner;
    Button increaseQuantityBtn;
    Button decreaseQuantityBtn;
    Button addToCart;


    Integer Quantity =1;
    TextView QuantityTxt;
    ItemListViewModel itemListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        BackBtn= (ImageButton) findViewById(R.id.back_button);
        increaseQuantityBtn=(Button)findViewById(R.id.quantity_increase);
        decreaseQuantityBtn=(Button)findViewById(R.id.quantity_decrease);
        QuantityTxt=findViewById(R.id.quantity_text);
        addToCart = (Button)findViewById(R.id.add_to_bag);

        vp = findViewById(R.id.image_slider);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);



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

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    CartEntity cartItem = new CartEntity();
                    cartItem.setProductId(getIntent().getStringExtra("Item ID"));
                    cartItem.setName(item.getName());
                    cartItem.setPrice(item.getPrice());
                    cartItem.setQuantity(Quantity);
                    cartItem.setImgUrl(item.getImg());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                CartDao cartDao = AppDatabase.getInstance(getApplicationContext()).cartDao();
                                cartDao.insert(cartItem);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Show success message to user
                                        Toast.makeText(ProductActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (Exception e) {
                                Log.e("AddToCart", "Error adding item to cart: " + e.getMessage());
                            }
                        }
                    }).start();

                }
            });


//        }catch (Exception e ){
//            Log.e("Quantitiy",e.getMessage());
//        }



    }
}