package com.example.ecommerceapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerceapplication.DatabaseClass.CartData;
import com.example.ecommerceapplication.Details;
import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.model.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductHolder> {
    FragmentActivity activity;
    List<CartData> cartList;
//    private int overallprice = 0;
//    private Object Context;
//    int msub = 0;
int overallprice = 0;

    public CartAdapter(FragmentActivity activity, List<CartData> productsArrayList) {
        this.activity = activity;
        this.cartList = productsArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cartitem, viewGroup, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ProductHolder productHolder, int i) {

        productHolder.textView1.setText(cartList.get(i).getProductName());
        productHolder.textview2.setText(cartList.get(i).getProductPrice());
        Picasso.with(activity).load(cartList.get(i).getImageUrl()).into(productHolder.imageView);


//        msub = Integer.valueOf(cartList.getProductPrice());
//       overallprice = overallprice + Integer.valueOf(cartList.get(i).getProductPrice());

//        productHolder.totaltext.setText(String.valueOf(overallprice));
    }



    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1,textview2,totaltext;
        Button confirm;
        public ProductHolder(@NonNull final View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.cartimg);
            textView1=itemView.findViewById(R.id.cartitemname);
            textview2=itemView.findViewById(R.id.itemprice);
            totaltext = itemView.findViewById(R.id.total);
            confirm = itemView.findViewById(R.id.confirm);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    String image= String.valueOf(cartList.get(position).getImageUrl());
                    String title=cartList.get(position).getProductName();
                    String price=cartList.get(position).getProductPrice();
                    String spec = cartList.get(position).getProductSpecification();
                    String id=cartList.get(position).getProductId();


                    Bundle bundle=new Bundle();
                    bundle.putString("imageUrl",image);
                    bundle.putString("ProductName",title);
                    bundle.putString("ProductPrice",price);
                    bundle.putString("ProductId",id);
                    bundle.putString("prodSpec",spec);

                    Intent i=new Intent(activity, Details.class);
                    i.putExtras(bundle);
                    activity.startActivity(i);
                }
            });
        }

    }


}