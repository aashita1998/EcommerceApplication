package com.example.ecommerceapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserAddress extends AppCompatActivity {

    TextView name, phonenumber, address, city, state, pincode, amount;
    Button continuePay;
    String Name;
    String phoneNumber;
    String Useraddress;
    String userCity;
    String userState;
    String userPincode;
    String number2;
    String total2;
    FloatingActionButton fabuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraddress);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        name = findViewById(R.id.addname);
        phonenumber = findViewById(R.id.addphone);
        address = findViewById(R.id.useraddress);
        city = findViewById(R.id.addcity);
        state = findViewById(R.id.addstate);
        pincode = findViewById(R.id.addpincode);
        continuePay = findViewById(R.id.continuepay);
        amount = findViewById(R.id.paynumber);
        fabuser = findViewById(R.id.fabua);


        ConnectivityManager connectivityManager = (ConnectivityManager) UserAddress.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            fabuser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserAddress.this);
                    builder.setTitle("Logout Confirmation!!!").
                            setMessage("You sure, you want to logout?");
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(getApplicationContext(),
                                            Login.class);
                                    startActivity(i);
                                }
                            });
                    builder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder.create();
                    alert11.show();
                }
            });

            Intent i = getIntent();
            number2 = i.getStringExtra("userpay");
            amount.setText(number2);

            total2 = amount.getText().toString();


            Bundle arguments = getIntent().getExtras();
            Name = arguments.getString("name");
            phoneNumber = arguments.getString("phone");
            Useraddress = arguments.getString("address");
            userCity = arguments.getString("city");
            userState = arguments.getString("state");
            userPincode = arguments.getString("pincode");

            name.setText(Name);
            phonenumber.setText(phoneNumber);
            address.setText(Useraddress);
            city.setText(userCity);
            state.setText(userState);
            pincode.setText(userPincode);


            continuePay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(UserAddress.this, Payment.class);
                    intent.putExtra("userpay", total2);
                    startActivity(intent);

                }
            });
        } else {
            if (UserAddress.this.getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT || UserAddress.this.getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(UserAddress.this);
                dialog.setMessage("No Internet Connection...Please Check Your Network");
                dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserAddress.this.finish();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }
        }

    }
}