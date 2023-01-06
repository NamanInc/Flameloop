package com.flameloopltd.flameloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.flameloopltd.flameloop.Activities.Splash_Screen;

public class MainActivity extends AppCompatActivity {


    // HELLLO SIR
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        Intent intent = new Intent(MainActivity.this, Splash_Screen.class);
        startActivity(intent);
        finish();
    }
}