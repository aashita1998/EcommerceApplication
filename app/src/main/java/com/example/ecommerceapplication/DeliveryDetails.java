package com.example.ecommerceapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class DeliveryDetails extends AppCompatActivity  {

    String payamount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        setContentView(R.layout.activity_delivery_details);


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(DeliveryDetails.this, Dashboard.class));
        finish();

    }
//    @Override
//    public void onPaymentSuccess(String s) {
//       // payamount = Integer.parseInt(paymentnuber.getText().toString());
//        Checkout checkout = new Checkout();
//        final Activity activity=this;
//        try{
//
//            JSONObject orderRequest = new JSONObject();
//            orderRequest.put("amount", number); // amount in the smallest currency unit
//            orderRequest.put("currency", "INR");
//            checkout.open(activity,orderRequest);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void onPaymentSuccess(String s) {
//        Toast.makeText(this, "Your payment successfull", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onPaymentError(int i, String s) {
//        Toast.makeText(this, "Your payment failed", Toast.LENGTH_SHORT).show();
//
//    }
}