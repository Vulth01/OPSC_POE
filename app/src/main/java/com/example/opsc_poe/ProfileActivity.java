package com.example.opsc_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    ImageButton btn_Gallery, btn_Settings, btn_Profile, btn_Home, btn_Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn_Gallery = findViewById(R.id.btn_Gallery);
        btn_Settings = findViewById(R.id.btn_Settings);
        btn_Profile = findViewById(R.id.btn_Profile);
        btn_Home = findViewById(R.id.btn_Home);
        btn_Info = findViewById(R.id.btn_Info);

        btn_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "btn_Gallery Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(ProfileActivity.this, GalleryActivity.class);
                startActivity(intentProfile);
            }
        });
        btn_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "btn_Settings Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intentProfile);
            }
        });
        btn_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "btn_Profile Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intentProfile);
            }
        });
        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "btn_Home Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intentProfile);
            }
        });
        btn_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "btn_Info Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(ProfileActivity.this, InfoActivity.class);
                startActivity(intentProfile);
            }
        });
    }
}