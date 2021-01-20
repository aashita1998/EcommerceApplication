package com.example.ecommerceapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapplication.DatabaseClass.CartData;
import com.example.ecommerceapplication.DatabaseClass.DataModel;
import com.example.ecommerceapplication.adapter.CartAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

public class Cart extends Fragment {
    StateRec recyclerView;
    ProgressDialog dialog;
    DataModel dataModel;
    List<CartData> cartDataList = new ArrayList<>();
    TextView totalprice;
    FloatingActionButton fabc;
    Button confirm;
    String total;

    //int overallprice=0;


    ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cart,container,false);
        recyclerView=view.findViewById(R.id.cart_rec);
        totalprice = view.findViewById(R.id.total);
        confirm = view.findViewById(R.id.confirm);
        dataModel= ViewModelProviders.of(getActivity(),viewModelFactory).get(DataModel.class);
        //cartDataList=new ArrayList<>();

        dialog=new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();


        loadFavData();
       // sendMessage();


        return view;
    }
    public void loadFavData(){
        dataModel.getListLiveData().observe(this, new Observer<List<CartData>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(@Nullable List<CartData> cartData) {
                cartDataList=cartData;
                CartAdapter cartAdapter=new CartAdapter(getActivity(),cartDataList);

                if(cartDataList.size()==-1){
                    Toast.makeText(getActivity(), "Cart is empty", Toast.LENGTH_SHORT).show();
                }
                if(getActivity().getApplication().getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                }
                else{
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
                }
                recyclerView.setAdapter(cartAdapter);


                int overallprice=0;
                for(int i=0;i<cartData.size();i++){
                    overallprice = overallprice + (Integer.parseInt(cartData.get(i).getProductPrice()));
                }

                totalprice.setText((String.valueOf(overallprice)));
                // total = totalprice.getText().toString();


                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total = totalprice.getText().toString();
                        Intent intent = new Intent(getActivity(), Shipping.class);
                        intent.putExtra("userpay",total);
                        startActivity(intent);

                    }
                });

            }

        });
        dialog.dismiss();
    }
//    public void sendMessage() {
//        Intent intent = new Intent(getActivity(), Payment.class);
//       // total = totalprice.getText().toString();
//        intent.putExtra("userpay", total);
//        startActivity(intent);
//    }


}