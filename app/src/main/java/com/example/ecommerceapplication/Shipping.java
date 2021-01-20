package com.example.ecommerceapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Shipping extends AppCompatActivity {
    EditText Name, PhoneNumber, Address, City, State, PinCode;
    Button nextre;
    FirebaseDatabase root;
    DatabaseReference reference;
    TextView payview;
    String number1;
    String total1;
    FloatingActionButton fabship;


    private Boolean validateName() {
        String value = Name.getText().toString();
        if (value.isEmpty()) {
            Name.setError("Please enter your name");
            return false;
        } else {
            Name.setError(null);
            return true;
        }
    }

    private Boolean validatePhone() {
        String value = PhoneNumber.getText().toString();
        if (value.isEmpty()) {
            PhoneNumber.setError("Please enter your phone number");
            return false;
        } else if (value.length() > 10 | value.length() < 10) {
            PhoneNumber.setError("Invalid Phone Number");
            return false;
        } else {
            PhoneNumber.setError(null);
            return true;
        }
    }

    private Boolean validateAddress() {
        String value = Address.getText().toString();
        if (value.isEmpty()) {
            Address.setError("Please enter the delivery address");
            return false;
        } else {
            Address.setError(null);
            return true;
        }
    }

    private Boolean validateCity() {
        String value = City.getText().toString();
        if (value.isEmpty()) {
            City.setError("Please enter your city");
            return false;
        } else {
            City.setError(null);
            return true;
        }
    }

    private Boolean validateState() {
        String value = State.getText().toString();
        if (value.isEmpty()) {
            State.setError("Please enter your state");
            return false;
        } else {
            State.setError(null);
            return true;
        }
    }

    private Boolean validatePincode() {
        String value = PinCode.getText().toString();
        if (value.isEmpty()) {
            PinCode.setError("Please enter the pincode");
            return false;
        } else if (value.length() > 6) {
            PinCode.setError("Invalid Pincode");
            return false;
        } else {
            PinCode.setError(null);
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        nextre = findViewById(R.id.nextact);
        Name = findViewById(R.id.name);
        PhoneNumber = findViewById(R.id.phone);
        Address = findViewById(R.id.address);
        City = findViewById(R.id.city);
        State = findViewById(R.id.state);
        PinCode = findViewById(R.id.pincode);
        payview = findViewById(R.id.payamount);
        fabship = findViewById(R.id.fabs);

        ConnectivityManager connectivityManager = (ConnectivityManager) Shipping.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            fabship.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Shipping.this);
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
            number1 = i.getStringExtra("userpay");
            payview.setText(number1);

            total1 = payview.getText().toString();


            nextre.setOnClickListener(v -> {
                if (!validateName() | !validatePhone() | !validateAddress() | !validateCity() | !validateState() | !validatePincode()) {
                    return;
                }


                root = FirebaseDatabase.getInstance();
                reference = root.getReference("delivery");

                String name = Name.getText().toString();
                String phone = PhoneNumber.getText().toString();
                String address = Address.getText().toString();
                String city = City.getText().toString();
                String state = State.getText().toString();
                String pincode = PinCode.getText().toString();


                ShippingHelperClass shippingHelperClass = new ShippingHelperClass(name, phone, address, city, state, pincode);
                reference.child(name).setValue(shippingHelperClass);


                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("phone", phone);
                bundle.putString("address", address);
                bundle.putString("city", city);
                bundle.putString("state", state);
                bundle.putString("pincode", pincode);


                Intent intentadd = new Intent(Shipping.this, UserAddress.class);
                intentadd.putExtras(bundle);
                intentadd.putExtra("userpay", total1);
                startActivity(intentadd);


            });
        } else {
            if (Shipping.this.getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT || Shipping.this.getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Shipping.this);
                dialog.setMessage("No Internet Connection...Please Check Your Network");
                dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Shipping.this.finish();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }
        }


    }
}