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

public class Toys extends Fragment {
    StateRec prodItems;
    Products products;
    RequestQueue requestQueue;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toys, container, false);
        prodItems = view.findViewById(R.id.toy_rec);

        requestQueue = Volley.newRequestQueue(getActivity());
        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products("1", "AKUNSKHI SET", "600", R.drawable.akunskhicompleteset,"Premium quality Toys a complete set of aunskhi members"));
        productsList.add(new Products("2", "TEAM-7 BAND", "350", R.drawable.completeband,"Premium quality band with all the friends of NARUTO"));
        productsList.add(new Products("3", "GARA TOY", "550", R.drawable.gara,"Premium quality GARA HOKAGE OF SAND VILLAGE toy"));
        productsList.add(new Products("4", "ITACHI TOY", "550", R.drawable.itachi,"Premium quality toy of ITACHI UCHIHA "));
        productsList.add(new Products("5", "MASTER JARAYA TOY", "550", R.drawable.masterjaraya,"Premium quality toy of MASTER JARAYA "));
        productsList.add(new Products("6", "KAKHASHI TOY", "550", R.drawable.kakhashi,"Premium quality toy of KAKHASHI with CHIDORI and SHARINGAN "));
        productsList.add(new Products("7", "KAKHASHI CHIDORI TOY", "650", R.drawable.kakhashichitori,"Premium quality toy of KAKHASHI with CHIDORI "));
        productsList.add(new Products("8", "KAKHASHI KEYCHAIN", "300", R.drawable.kakhashikeychain,"Premium quality keychain of KAKHASHI with PAKKU"));
        productsList.add(new Products("9", "3 in 1 COMPLETE SET", "550", R.drawable.keychain,"Premium quality of 3 keychains including ITACHI UCHIHA, SASUKE UCHIHA, KAKHASHI HATAKE"));
        productsList.add(new Products("10", "NARUTO KEYCHAIN", "350", R.drawable.keychainnaruto,"Premium quality keychain of NARUTO with his favourite RAMEN NOODLES "));
        productsList.add(new Products("11", "MADARA UCHIHA", "550", R.drawable.madara,"Premium quality toy of MADARA "));
        productsList.add(new Products("12", "NARUTO KEYCHAIN", "350", R.drawable.narutokeychain1,"Premium quality keychain of NARUTO"));
        productsList.add(new Products("13", "NARUTO KEYCHAIN", "350", R.drawable.narutokeychain,"Premium quality keychain of NARUTO"));
        productsList.add(new Products("14", "NARUTO SAGE MODE", "650", R.drawable.narutosage,"Premium quality toy of NARUTO parcticing SAGE MODE with his MASTER"));
        productsList.add(new Products("15", "SASUKE UCHIHA", "550", R.drawable.sasuke,"Premium quality toys of SASUKE UCHIHA"));
        productsList.add(new Products("16", "SASUKE CHIDORE", "650", R.drawable.sasukechitore,"Premium quality toy of SASUKE UCHIHA with CHIDORI"));
        productsList.add(new Products("17", "SASUKE TOY", "650", R.drawable.sasukecursemark,"Premium quality toy of powerful SASUKE UCHIHA"));
        productsList.add(new Products("18", "TOBI TOY", "550", R.drawable.tobi,"Premium quality toy of TOBI"));
        productsList.add(new Products("19", "TSUNADE TOY", "550", R.drawable.granny,"Premium quality toy of TUSADE the LEGENDARY SUNNIN"));

        setProdItems(productsList);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            prodItems.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        } else {
            prodItems.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }

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
