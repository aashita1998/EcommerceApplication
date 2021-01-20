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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

public class Cups extends Fragment {

StateRec prodItems;
    Products products;
    RequestQueue requestQueue;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cups, container, false);
        prodItems = view.findViewById(R.id.cup_rec);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            prodItems.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        } else {
            prodItems.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
        requestQueue = Volley.newRequestQueue(getActivity());
        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products("1", "HOKAGE CUP", "550", R.drawable.hokagea,"Premium quality cup of HOKAGE NARUTO"));
        productsList.add(new Products("2", "NARUTO CUP", "650", R.drawable.narutocuo,"Premium quality cup of NARUTO"));
        productsList.add(new Products("3", "NARUTO-HINATA CUP", "750", R.drawable.narutohinata,"Premium quality cup of NARUTO and HINATA"));
        productsList.add(new Products("4", "NARUTO-SASUKE CUP", "750", R.drawable.sasukeacup,"Premium quality cup of NARUTO and SASUKE"));
        productsList.add(new Products("5", "SASUKE CUP", "550", R.drawable.sasukecup,"Premium quality cup of SASUKE"));
        productsList.add(new Products("6", "9-TAIL SEALING CUP", "350", R.drawable.sealingmark,"Premium quality cup of 9 tail sealing mark of NARUTO"));

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

