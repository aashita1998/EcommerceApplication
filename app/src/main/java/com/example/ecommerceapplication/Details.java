package com.example.ecommerceapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ecommerceapplication.DatabaseClass.CartData;
import com.example.ecommerceapplication.DatabaseClass.DataModel;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {
    ImageView prodimg;
    TextView prodname,prodprice,spec;
    LikeButton favoriteButton;
    Integer poster;
    String title;
    String price;
    String specification;
    String id;
    RequestQueue requestQueue;
    List<CartData> cartDataList;
    DataModel dataModel;
    ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        prodimg = findViewById(R.id.prodimg);
        prodname = findViewById(R.id.prodname);
        prodprice = findViewById(R.id.prodprice);
        spec = findViewById(R.id.prodSpecID);
        favoriteButton=findViewById(R.id.fav);
        dataModel= ViewModelProviders.of(this,viewModelFactory).get(DataModel.class);
        requestQueue= Volley.newRequestQueue(this);
        cartDataList=new ArrayList<>();
        Bundle arguments=getIntent().getExtras();
        poster = Integer.valueOf(arguments.getString("imageUrl"));
        title = arguments.getString("ProductName");
        price = arguments.getString("ProductPrice");
        id= arguments.getString("ProductId");
        specification = arguments.getString("prodSpec");
        Picasso.with(this).load(poster).into(prodimg);
        prodname.setText(title);
        prodprice.setText(price);
        spec.setText(specification);
        addFav();
         searchforProduct();
    }

    private void searchforProduct() {

        dataModel.getListLiveData().observe(this, new Observer<List<CartData>>() {
            @Override
            public void onChanged(@Nullable List<CartData> cartData) {
                for (int i=0;i<cartData.size();i++)
                {

                    String d=cartData.get(i).getProductId();
                    if (d.equalsIgnoreCase(id))
                    {
                        favoriteButton.setLiked(true);

                    }

                }
            }
        });

    }


    public void addFav(){
        favoriteButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                saveData();
                Toast.makeText(Details.this, "Added To Your Cart", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void unLiked(LikeButton likeButton) {

                deletedata();
                Toast.makeText(Details.this, "Removed From Your Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deletedata() {
            CartData cartData=new CartData();
            cartData.setProductName(title);
            cartData.setImageUrl(poster);
            cartData.setProductPrice(price);
            cartData.setProductId(id);
            cartData.setProductSpecification(specification);
            dataModel.deletedata(cartData);
        }


    public  void saveData(){
        CartData cartData=new CartData();
        cartData.setProductName(title);
        cartData.setImageUrl(poster);
        cartData.setProductPrice(price);
        cartData.setProductId(id);
        cartData.setProductSpecification(specification);
        dataModel.insert(cartData);

    }
}