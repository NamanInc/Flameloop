package com.flameloopltd.flameloop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.flameloopltd.flameloop.R;

public class Select_Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        getSupportActionBar().hide();
    }
}