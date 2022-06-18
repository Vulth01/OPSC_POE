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

    ImageButton btn_Gallery, btn_Settings, btn_Profile, btn_Home, btn_Info;
    Button btnLogin, btnSignUp;
    EditText edtUsername, edtPassword;

    FirebaseAuth fAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef;
    boolean userFlag, passFlag;
    int details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        btn_Gallery = findViewById(R.id.btn_Gallery);
//        btn_Settings = findViewById(R.id.btn_Settings);
//        btn_Profile = findViewById(R.id.btn_Profile);
//        btn_Home = findViewById(R.id.btn_Home);
//        btn_Info = findViewById(R.id.btn_Info);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);


        btnLogin.setOnClickListener(new View.OnClickListener() {


//            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }

            @Override
            public void onClick(View view) {

                //pull data from database and check if usernameIndex == passwordIndex
                // since they will be added at same time thus have same index
                // (find the index of the username and the password and see if they are the same)
                // if equal -> return true and print login success -> go to main screen;
                // else -> return false and print login failed

                DatabaseReference ref = database.getReference("message");
                DatabaseReference usernameRef = database.getReference().child("Username").child("username");

                usernameRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i = 0;
                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            if (edtUsername.getText().toString().equals(snapshot1.getValue().toString())){
                                userFlag = true;
                                details++;
                                Toast.makeText(ProfileActivity.this, "username correct", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else if (i == snapshot.getChildrenCount()){
                                Toast.makeText(ProfileActivity.this, "No such username", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });

                DatabaseReference passwordRef = database.getReference().child("Password").child("password");

                passwordRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i = 0;
                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            if (edtPassword.getText().toString().equals(snapshot1.getValue().toString())){
                                passFlag = true;
                                details++;
                                Toast.makeText(ProfileActivity.this, "Password correct", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else if (i == snapshot.getChildrenCount()){
                                Toast.makeText(ProfileActivity.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });

                Toast.makeText(ProfileActivity.this, "userflag: " + userFlag + " + passFlag: " + passFlag, Toast.LENGTH_SHORT).show();

                if (userFlag && passFlag)
                {
                    Toast.makeText(ProfileActivity.this, "Proceeding to menu...", Toast.LENGTH_SHORT).show();
                    Intent intentProfile = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intentProfile);
                    return;
                }
                else
                {
                    Toast.makeText(ProfileActivity.this, "Incorrect Details", Toast.LENGTH_SHORT).show();

                }
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



//        int i;
//        Firebase.setAndroidContext(this);
//        Firebase myFirebaseRef = new Firebase("https://<your_firebase>/");
//        myFirebaseRef.child("Europe").child("Albania").child("answers").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
//                int length = (int) dataSnapshot.getChildrenCount();
//                String[] sampleString = new String[length];
//                while(i < length) {
//                    sampleString[i] = iterator.next().getValue().toString();
//                    Log.d(Integer.toString(i), sampleString[i]);
//                    i++;
//                }
//            }


//        btn_Gallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ProfileActivity.this, "btn_Gallery Pressed", Toast.LENGTH_SHORT).show();
//                Intent intentProfile = new Intent(ProfileActivity.this, GalleryActivity.class);
//                startActivity(intentProfile);
//            }
//        });
//        btn_Settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ProfileActivity.this, "btn_Settings Pressed", Toast.LENGTH_SHORT).show();
//                Intent intentProfile = new Intent(ProfileActivity.this, SettingsActivity.class);
//                startActivity(intentProfile);
//            }
//        });
////        btn_Profile.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Toast.makeText(ProfileActivity.this, "btn_Profile Pressed", Toast.LENGTH_SHORT).show();
////                Intent intentProfile = new Intent(ProfileActivity.this, ProfileActivity.class);
////                startActivity(intentProfile);
////            }
////        });
//        btn_Home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ProfileActivity.this, "btn_Home Pressed", Toast.LENGTH_SHORT).show();
//                Intent intentProfile = new Intent(ProfileActivity.this, MainActivity.class);
//                startActivity(intentProfile);
//            }
//        });
//        btn_Info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ProfileActivity.this, "btn_Info Pressed", Toast.LENGTH_SHORT).show();
//                Intent intentProfile = new Intent(ProfileActivity.this, InfoActivity.class);
//                startActivity(intentProfile);
//            }
//        });
    }
}