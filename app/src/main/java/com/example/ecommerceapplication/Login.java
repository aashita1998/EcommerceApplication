package com.example.ecommerceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
Button callsignup,forgotpass,go;
ImageView imageone,imagetwo,imagethree,imagefour;
TextView hi,signtext;
ProgressBar progressBar;
TextInputLayout username,password;
SharedPreferences sp;


    private Boolean validateUsername() {
        String value = username.getEditText().getText().toString();
        if(value.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }
        else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String value = password.getEditText().getText().toString();
        if(value.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        setContentView(R.layout.activity_login);

        callsignup = findViewById(R.id.signup);
        forgotpass = findViewById(R.id.forget);
        go = findViewById(R.id.go);
        imageone = findViewById(R.id.first);
        imagetwo = findViewById(R.id.second);
        imagethree = findViewById(R.id.third);
        imagefour = findViewById(R.id.fourth);
        hi = findViewById(R.id.hi);
        signtext = findViewById(R.id.signtext);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        sp = getSharedPreferences("login",MODE_PRIVATE);

        if(sp.getBoolean("logged",false)) {
            Intent intent1 = new Intent(Login.this, Dashboard.class);
            startActivity(intent1);
        }

        if (CheckConnectivity.isInternetAvailable(Login.this)) {

            forgotpass.setOnClickListener(v -> {
                Intent intent = new Intent(Login.this, PasswordCode.class);
                startActivity(intent);
            });

            callsignup.setOnClickListener(v -> {
                Intent intent = new Intent(Login.this, signup.class);
                startActivity(intent);
            });

            go.setOnClickListener((View v) -> {
                if (!validateUsername() | !validatePassword()) {
                    return;
                } else {
                        isUser();
                    sp.edit().putBoolean("logged",true).apply();
                }
            });
        }
        else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }


    private void isUser() {
        String userenteredname = username.getEditText().getText().toString().trim();
        String userenteredpassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userenteredname);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordDB = snapshot.child(userenteredname).child("password").getValue(String.class);

                    if(passwordDB!=null && passwordDB.equals(userenteredpassword)){

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String fullnameDB = snapshot.child(userenteredname).child("fullname").getValue(String.class);
                        String usernameDB = snapshot.child(userenteredname).child("username").getValue(String.class);
                        String emailDB = snapshot.child(userenteredname).child("email").getValue(String.class);
                        String phoneDB = snapshot.child(userenteredname).child("phone").getValue(String.class);
                        Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);

                        intent.putExtra("fullname",fullnameDB);
                        intent.putExtra("username",usernameDB);
                        intent.putExtra("email",emailDB);
                        intent.putExtra("phone",phoneDB);
                        intent.putExtra("password",passwordDB);
                        finish();

                        startActivity(intent);



                    }
                    else{
//                        progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else{
//                    progressBar.setVisibility(View.GONE);
                    username.setError("No such user exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}