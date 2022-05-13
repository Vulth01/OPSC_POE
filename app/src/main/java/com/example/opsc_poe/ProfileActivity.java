package com.example.opsc_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    ImageButton btn_Gallery, btn_Settings, btn_Profile, btn_Home, btn_Info;
    Button btnLogin, btnSignUp;
    EditText edtUsername, edtPassword;

    FirebaseAuth fAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn_Gallery = findViewById(R.id.btn_Gallery);
        btn_Settings = findViewById(R.id.btn_Settings);
        btn_Profile = findViewById(R.id.btn_Profile);
        btn_Home = findViewById(R.id.btn_Home);
        btn_Info = findViewById(R.id.btn_Info);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtUsername.getText().toString().equals("Username1") && edtPassword.getText().toString().equals("Password1"))
                {
                    Toast.makeText(ProfileActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Toast.makeText(ProfileActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(ProfileActivity.this, "btnLogin Pressed", Toast.LENGTH_SHORT).show();

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference myRef = database.getReference("message");
                DatabaseReference usernameRef = database.getReference("Username").child("username");
                DatabaseReference passwordRef = database.getReference("Password").child("password");
                usernameRef.push().setValue(edtUsername.getText().toString());
                passwordRef.push().setValue(edtPassword.getText().toString());

                Toast.makeText(ProfileActivity.this, "btnSignUp Pressed", Toast.LENGTH_SHORT).show();
            }
        });




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