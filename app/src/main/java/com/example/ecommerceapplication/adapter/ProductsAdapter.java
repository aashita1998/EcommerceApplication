package com.example.ecommerceapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerceapplication.Details;
import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.model.Products;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    FragmentActivity activity;
    List<Products> productsList;

    public ProductsAdapter(FragmentActivity activity, List<Products> productsList) {
        this.activity = activity;
        this.productsList = productsList;
    }


    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mainscreen,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

        Picasso.with(activity).load(productsList.get(position).getImageUrl()).into(holder.prodImg);
        holder.prodName.setText(productsList.get(position).getProductName());
        holder.pricenum.setText(productsList.get(position).getProductPrice());
//        holder.specification.setText(productsList.get(position).getProductSpecification());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView prodImg;
        TextView prodName,pricenum,specification;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
           prodImg=itemView.findViewById(R.id.img);
            prodName=itemView.findViewById(R.id.prodname);
            pricenum=itemView.findViewById(R.id.pricenum);
            //specification = itemView.findViewById(R.id.prodSpecID);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Integer image=productsList.get(position).getImageUrl();
            String  prodName=productsList.get(position).getProductName();
            String  price=productsList.get(position).getProductPrice();
            String id= productsList.get(position).getProductId();
            String prodSpec = productsList.get(position).getProductSpecification();
            Bundle arguments=new Bundle();
            arguments.putString("imageUrl", String.valueOf(image));
            arguments.putString("ProductName",prodName);
            arguments.putString("ProductPrice",price);
            arguments.putString("ProductId", id);
            arguments.putString("prodSpec",prodSpec);
            Intent i=new Intent(activity, Details.class);
            i.putExtras(arguments);
            activity.startActivity(i);

        }
    }
}