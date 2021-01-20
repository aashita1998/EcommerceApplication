package com.example.ecommerceapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ecommerceapplication.adapter.ProductsAdapter;
import com.example.ecommerceapplication.model.Products;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Tshirts extends Fragment {
    StateRec prodItems;
    Products products;
    RequestQueue requestQueue;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tshirts, container, false);
        prodItems = view.findViewById(R.id.tshirts_rec);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            prodItems.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        } else {
            prodItems.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
        requestQueue = Volley.newRequestQueue(getActivity());
        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products("1", "JARAYA T-SHIRT", "350", R.drawable.jaraya,"Premium quality T-shirt with MASTER JARAYA picture printed. It is wrinkle free"));
        productsList.add(new Products("2", "KAKHASHI T-SHIRT", "450", R.drawable.kakhashitshirt,"Premium quality T-shirt with KAKHASHI picture printed. It is wrinkle free"));
        productsList.add(new Products("3", "NARUTO BORUTO T-SHIRT", "350", R.drawable.narutoboruto,"Premium quality T-shirt with NARUTO and BORUTO pictures printed. It is wrinkle free"));
        productsList.add(new Products("4", "NARUTO T-SHIRT", "550", R.drawable.narutografty,"Premium quality T-shirt with NARUTO picture printed. It is wrinkle free and a kind of hoodie type tshirt"));
        productsList.add(new Products("5", "NARUTO T-SHIRT", "350", R.drawable.narutok,"Premium quality T-shirt with NARUTO picture printed. It is wrinkle free"));
        productsList.add(new Products("6", "NARUTO T-SHIRT", "350", R.drawable.narutored,"Premium quality T-shirt with NARUTO picture printed. It is wrinkle free"));
        productsList.add(new Products("7", "NARUTO T-SHIRT", "450", R.drawable.narutoshirt,"Premium quality T-shirt with NARUTO picture printed. It is wrinkle free"));
        productsList.add(new Products("8", "NARUTO T-SHIRT", "300", R.drawable.narutotshirt,"Premium quality T-shirt with NARUTO picture printed. It is wrinkle free"));
        productsList.add(new Products("9", "TEAM-7 T-SHIRT", "450", R.drawable.team7,"Premium quality T-shirt with Team-7 picture printed. It is wrinkle free"));
        productsList.add(new Products("10", "MINATO T-SHIRT", "350", R.drawable.tshirt,"Premium quality T-shirt with MINATO THE 4th HOKAGE picture printed. It is wrinkle free"));
        setProdItems(productsList);

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            setProdItems(productsList);
        } else {
            if (getActivity().getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT || getActivity().getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setMessage("No Internet Connection...Please Check Your Network");
                dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }
        }
        return view;
    }

    private void setProdItems(List<Products> productsList) {
        ProductsAdapter adapter = new ProductsAdapter(getActivity(), productsList);
        prodItems.setAdapter(adapter);
    }

}
