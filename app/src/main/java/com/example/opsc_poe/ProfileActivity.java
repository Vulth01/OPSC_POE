package com.example.opsc_poe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.Iterator;

public class ProfileActivity extends AppCompatActivity {

    Button btnLogin, btnSignUp;
    EditText edtUsername, edtPassword;

    FirebaseAuth fAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference dbRef;
    boolean userFlag, passFlag;
    int details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref = database.getReference("message");
                DatabaseReference usernameRef = database.getReference().child("Username").child("username");

                usernameRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i = 0;
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            if (edtUsername.getText().toString().equals(snapshot1.getValue().toString())) {
                                DatabaseReference passwordRef = database.getReference().child("Password").child("password");
                                passwordRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int i = 0;
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                            if (edtPassword.getText().toString().equals(snapshot1.getValue().toString())) {
                                                Toast.makeText(ProfileActivity.this, "Proceeding to menu...", Toast.LENGTH_SHORT).show();
                                                Intent intentProfile = new Intent(ProfileActivity.this, MainActivity.class);
                                                startActivity(intentProfile);
                                                return;
                                            }
                                            else {
                                                i++;
                                                if (i == snapshot.getChildrenCount()) {
                                                    Toast.makeText(ProfileActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                                return;
                            }
                            else {
                                i++;
                                if (i == snapshot.getChildrenCount()) {
                                    Toast.makeText(ProfileActivity.this, "Incorrect username", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
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


    }
}