package com.example.opsc_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_Gallery, btn_Settings, btn_Info, btn_Profile, btnChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChart = findViewById(R.id.btn_Chart);


        btn_Gallery = findViewById(R.id.btn_Gallery);
        btn_Settings = findViewById(R.id.btn_Settings);
        btn_Profile = findViewById(R.id.btn_Profile);
        btn_Info = findViewById(R.id.btn_Info);

        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn_Profile Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(MainActivity.this, PieChartActivity.class);
                startActivity(intentProfile);
            }
        });


        btn_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn_Gallery Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intentProfile);
            }
        });
        btn_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn_Settings Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentProfile);
            }
        });
        btn_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn_Profile Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intentProfile);
            }
        });
        btn_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn_Info Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intentProfile);
            }
        });

    }
}