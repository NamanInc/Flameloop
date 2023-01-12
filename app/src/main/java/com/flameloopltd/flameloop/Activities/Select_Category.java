package com.flameloopltd.flameloop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.flameloopltd.flameloop.Adapterss.Grid_cat_adapter;
import com.flameloopltd.flameloop.Models.Category_Model;
import com.flameloopltd.flameloop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Select_Category extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference databaseReference;

    GridView gridView;
    Grid_cat_adapter grid_cat_adapter;


    List<Category_Model> category_modelList;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        getSupportActionBar().hide();


        // HELLLOO!

        //Initialising variables

        category_modelList = new ArrayList<>();

        gridView = findViewById(R.id.grid_view);
        grid_cat_adapter = new Grid_cat_adapter(Select_Category.this,category_modelList);
        gridView.setAdapter(grid_cat_adapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.show();


        //FIREBASE
        database = FirebaseDatabase.getInstance();

           //Getting reference a root node "IMAGE" then a child node "CATEGORY_IMAGE"

        databaseReference = database.getReference("FLAMELOOP_IMAGES").child("CATEGORY");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                retrieve_data(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Select_Category.this, category_modelList.get(i).getCATEGORY_TEXT(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void retrieve_data(DataSnapshot snapshot) {

        category_modelList.clear();

        if(snapshot.exists())
        {
            // YES, snapshot has content

            progressDialog.dismiss();
            for(DataSnapshot dataSnapshot : snapshot.getChildren())
            {
                String CATEGORY_ID = dataSnapshot.child("category_ID").getValue(String.class);
                String CATEGORY_PICTURE = dataSnapshot.child("category_PICTURE").getValue(String.class);
                String CATEGORY_TEXT =  dataSnapshot.child("category_TEXT").getValue(String.class);
                String CATEGORY_COLOR = dataSnapshot.child("category_COLOR").getValue(String.class);


                Log.d("DATABASE_UPDATE",CATEGORY_ID+"\n"
                +CATEGORY_PICTURE+"\n"+CATEGORY_TEXT+"\n"+CATEGORY_COLOR);



                try{
                    Category_Model model = new Category_Model(CATEGORY_ID,CATEGORY_PICTURE,CATEGORY_TEXT,CATEGORY_COLOR);

                    category_modelList.add(model);
                    grid_cat_adapter.notifyDataSetChanged();

                }
                catch (Exception e)
                {
                    Log.d("DATABASE_UPDATE","\n"+e.getLocalizedMessage());
                }


            }



        }


    }
}