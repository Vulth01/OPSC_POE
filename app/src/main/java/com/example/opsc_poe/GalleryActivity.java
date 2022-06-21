package com.example.opsc_poe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class GalleryActivity extends AppCompatActivity {
    //test
    int translationY = 1200, translationX = -200, btnNum = 0, goal = 6;
    EditText edtGoal, edtNewDescription, edtNewName;
    Category[] categories;
    ImageButton btn_Gallery, btn_Settings, btn_Profile, btn_Home, btn_Info;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        btn_Gallery = findViewById(R.id.btn_Gallery);
        btn_Settings = findViewById(R.id.btn_Settings);
        btn_Profile = findViewById(R.id.btn_Profile);
        btn_Home = findViewById(R.id.btn_Home);
        btn_Info = findViewById(R.id.btn_Info);
        progressBar = findViewById(R.id.progressBar);

        AddListeners();
        CreateCategory();

        //Log.d("One", "START");
    }

    public void onClick(View view) {
        Intent intentProfile = new Intent(GalleryActivity.this, CategoryActivity.class);
        startActivity(intentProfile);
    }

    private void CreateCategory(){
        if (btnNum < goal){

            progressBar.incrementProgressBy(100/goal);
            ConstraintLayout constraintLayout;
            constraintLayout = findViewById(R.id.activityCategory);

            btnNum++;
            if (translationX < 600){
                translationX += 300;
            }
            else{
                translationX = -200 + 300;
                translationY += 200;
            }
            Button btnCategory = new Button(GalleryActivity.this);
            btnCategory.setId(btnNum);
            categories[btnNum - 1] = new Category();
            categories[btnNum - 1].Name = edtNewName.getText().toString();
            categories[btnNum - 1].Description = edtNewDescription.getText().toString();

            btnCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(GalleryActivity.this, Integer.toString(btnCategory.getId()), Toast.LENGTH_SHORT).show();
                    Intent intentProfile = new Intent(GalleryActivity.this, CategoryActivity.class);
                    startActivity(intentProfile);
                }
            });

            btnCategory.setTranslationX(translationX);
            btnCategory.setTranslationY(translationY);
            btnCategory.setText(Integer.toString(btnCategory.getId()));

            constraintLayout.addView(btnCategory);

        }
        else{
            Toast.makeText(GalleryActivity.this, "Goal Reached!", Toast.LENGTH_SHORT).show();
        }
    }

    private void AddListeners(){
        btn_Gallery.setOnClickListener(view -> {
            Toast.makeText(GalleryActivity.this, "btn_Gallery Pressed", Toast.LENGTH_SHORT).show();
            Intent intentProfile = new Intent(GalleryActivity.this, GalleryActivity.class);
            startActivity(intentProfile);
        });
        btn_Settings.setOnClickListener(view -> {
            Toast.makeText(GalleryActivity.this, "btn_Settings Pressed", Toast.LENGTH_SHORT).show();
            Intent intentProfile = new Intent(GalleryActivity.this, SettingsActivity.class);
            startActivity(intentProfile);
        });
        btn_Profile.setOnClickListener(view -> {
            Toast.makeText(GalleryActivity.this, "btn_Profile Pressed", Toast.LENGTH_SHORT).show();
            Intent intentProfile = new Intent(GalleryActivity.this, ProfileActivity.class);
            startActivity(intentProfile);
        });
        btn_Home.setOnClickListener(view -> {
            Toast.makeText(GalleryActivity.this, "btn_Home Pressed", Toast.LENGTH_SHORT).show();
            Intent intentProfile = new Intent(GalleryActivity.this, MainActivity.class);
            startActivity(intentProfile);
        });

        btn_Info.setOnClickListener(view -> {
            Toast.makeText(GalleryActivity.this, "btn_Info Pressed", Toast.LENGTH_SHORT).show();
            Intent intentProfile = new Intent(GalleryActivity.this, InfoActivity.class);
            startActivity(intentProfile);
        });
    }


}