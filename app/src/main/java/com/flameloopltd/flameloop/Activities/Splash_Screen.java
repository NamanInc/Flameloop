package com.flameloopltd.flameloop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.flameloopltd.flameloop.R;
import com.google.firebase.auth.FirebaseAuth;

public class Splash_Screen extends AppCompatActivity {

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (firebaseAuth.getCurrentUser()!=null){

                    Intent intent = new Intent(getApplicationContext(),Select_Category.class);
                    startActivity(intent);
                    finish();



                }else {

                    Intent intent = new Intent(getApplicationContext(),GetTsratedNew.class);
                    startActivity(intent);
                    finish();


                }



            }
        },3000);
    }
}