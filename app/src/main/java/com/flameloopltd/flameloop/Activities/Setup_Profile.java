package com.flameloopltd.flameloop.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flameloopltd.flameloop.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Setup_Profile extends AppCompatActivity {
    private RelativeLayout mainlayout;
    private ImageView profiledpimg;
    public static final int PICK_CODE = 12;
    private Uri imageuri;
    private TextView emailtxt;
    private TextView nametxt;
    private TextView biotxt;
    private Button completesetupbtn;
    private String email , name , bio;

    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);
        getSupportActionBar().hide();

        mainlayout = findViewById(R.id.click_layout);
        profiledpimg = findViewById(R.id.imageview_profile);

        emailtxt = findViewById(R.id.email_txt);
        nametxt = findViewById(R.id.nametxt);
        biotxt = findViewById(R.id.bio_txt);
        completesetupbtn = findViewById(R.id.completesetup_btn);
       storageReference = FirebaseStorage.getInstance().getReference("Upload");


        mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,PICK_CODE);



            }


        });

        completesetupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cheaktextutils();
            }
        });








    }

    private void cheaktextutils() {


        name = nametxt.getText().toString().trim();
        email = emailtxt.getText().toString().trim();
        bio = biotxt.getText().toString().trim();


        if (TextUtils.isEmpty(name)){

            Toast.makeText(this, "Please Enter your name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email)){

            Toast.makeText(this, "Enter your Email id", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(bio)){

            Toast.makeText(this, "Bio cannot be empty", Toast.LENGTH_SHORT).show();
        }

        else if (imageuri == null) {

            Toast.makeText(this, "Select a profile", Toast.LENGTH_SHORT).show();


        }
        else {

            uploadimagetodatabase();
        }
    }

    private void uploadimagetodatabase() {



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CODE && resultCode == RESULT_OK && data!=null){

            imageuri = data.getData();

            profiledpimg.setImageURI(imageuri);




        }
    }
}