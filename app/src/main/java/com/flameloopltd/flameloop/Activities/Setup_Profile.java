package com.flameloopltd.flameloop.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flameloopltd.flameloop.Models.User_Model;
import com.flameloopltd.flameloop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.regex.Pattern;

public class Setup_Profile extends AppCompatActivity {

    //     ALL GLOBAL VARIABLES


    private RelativeLayout mainlayout;
    private ImageView profiledpimg;
    public static final int PICK_CODE = 12;
    private Uri imageuri;
    private EditText EMAIL_EDIT_TEXT;
    private EditText NAME_EDIT_TEXT;
    private EditText BIO_EDIT_TEXT;
    private Button completesetupbtn;
    private String email , name , bio;


    private StorageReference storageReference;

    // FIREBASE VARIABLES

    FirebaseDatabase firebaseDatabase;
    DatabaseReference USER_NODE_ROOT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);
        getSupportActionBar().hide();


        // initialising id's

        mainlayout = findViewById(R.id.click_layout);
        profiledpimg = findViewById(R.id.imageview_profile);
        EMAIL_EDIT_TEXT = findViewById(R.id.email_txt);
        NAME_EDIT_TEXT = findViewById(R.id.nametxt);
        BIO_EDIT_TEXT = findViewById(R.id.bio_txt);
        completesetupbtn = findViewById(R.id.completesetup_btn);
        storageReference = FirebaseStorage.getInstance().getReference("Upload");


        //GETTING USER_ROOT_NODE

        firebaseDatabase = FirebaseDatabase.getInstance();
        USER_NODE_ROOT = firebaseDatabase.getReference("FLAMELOOP_USERS");







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
                
                // this will check all the fields
                boolean credential_status = cheaktextutils();
                
                // if true then get data from fields and make a object
                if(credential_status)
                {
                    User_Model new_user = getDataFromFields();

                    //UPLOADING USER DETAILS TO FIREBASE : REALTIME DATABASE
                    createUserInRDB(new_user);

                }

            }



        });








    }




    //  THIS METHOD WILL CREATE A USER IN REAL TIME DATABASE

    private void createUserInRDB(User_Model new_user) {

        // TODO
        //  GET UID OF USER AND PASS THAT AS CHILD



        USER_NODE_ROOT.child(new_user.getUSER_FULL_NAME()).setValue(new_user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                    Toast.makeText(getApplicationContext(),"User Created!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"User Not Created!",Toast.LENGTH_LONG).show();

            }


        });
    }



    //  THUS METHOD WILL GET DATA FROM FIELDS, WILL MAKE A OBJECT OF USER_MODEL

    private User_Model getDataFromFields() {
        
        // CREATING A NEW USER 

        User_Model new_user = new User_Model();
        
        
        // GETTING TEXT TO STRINGS
        
        String USER_FULL_NAME = NAME_EDIT_TEXT.getText().toString();
        String USER_EMAIL = EMAIL_EDIT_TEXT.getText().toString();
        String USER_BIO = BIO_EDIT_TEXT.getText().toString();


        // setting up the model
        new_user.setUSER_FULL_NAME(USER_FULL_NAME);
        new_user.setUSER_EMAIL(USER_EMAIL);
        new_user.setUSER_INFO(USER_BIO);
        

        return new_user;
        
    }







    private boolean cheaktextutils() {


        name = NAME_EDIT_TEXT.getText().toString().trim();
        email = EMAIL_EDIT_TEXT.getText().toString().trim();
        bio = BIO_EDIT_TEXT.getText().toString().trim();


        if (TextUtils.isEmpty(name)){

//            Toast.makeText(this, "Please Enter your name", Toast.LENGTH_SHORT).show();
            NAME_EDIT_TEXT.setError("Please Enter your name");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

//            Toast.makeText(this, "Enter your Email id", Toast.LENGTH_SHORT).show();
            EMAIL_EDIT_TEXT.setError("Please enter a valid Email Address");
            return false;

        }
        else if (TextUtils.isEmpty(bio)){

//            Toast.makeText(this, "Bio cannot be empty", Toast.LENGTH_SHORT).show();
            return false;

        }

//        else if (imageuri == null) {
//
//            Toast.makeText(this, "Select a profile", Toast.LENGTH_SHORT).show();
//
//
//        }
        else {

            uploadimagetodatabase();
        }
        return true;
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