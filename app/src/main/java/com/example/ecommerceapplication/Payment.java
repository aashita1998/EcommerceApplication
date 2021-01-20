package com.example.ecommerceapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
//

public class Payment extends AppCompatActivity implements PaymentResultListener{

    TextView paymentnuber;
    Button razor;
    Button cod;
    String number;
    int payamount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        setContentView(R.layout.activity_payment);
        paymentnuber = findViewById(R.id.paymentmoney);
        razor = findViewById(R.id.razorbutton);
        cod = findViewById(R.id.codbuttn);


        Intent i = getIntent();
        number = i.getStringExtra("userpay");
        paymentnuber.setText(number);


        razor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this,DeliveryDetails.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void startPayment() {
        payamount = Integer.parseInt(paymentnuber.getText().toString());
        Checkout checkout = new Checkout();
        final Activity activity = this;
        try {

            JSONObject orderRequest = new JSONObject();

            orderRequest.put("currency", "INR");
            orderRequest.put("amount", payamount *100); // amount in the smallest currency unit
            checkout.open(activity, orderRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Your payment successfull", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Your payment failed", Toast.LENGTH_SHORT).show();

    }
}
