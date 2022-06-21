package com.example.opsc_poe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    int translationY = 1000, translationX = 0, btnNum = 0, goal = 6;
    long numItems;
    Items[] items;
    ImageButton btn_Gallery;
    ProgressBar progressBar;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    DatabaseReference itemsRef;

    ImageView imageView;
    TextView txtGoal, txtNewName, txtNewDescription;
    EditText edtGoal, edtNewDescription, edtNewName;
    Button btnAddItems;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        edtGoal = findViewById(R.id.edtGoal);
        edtGoal.setText("9");

        goal = Integer.parseInt(edtGoal.getText().toString());
        items = new Items[goal];
        progressBar = findViewById(R.id.progressBar);
        edtNewDescription = findViewById(R.id.edtNewDescription);
        edtNewName = findViewById(R.id.edtNewName);

        ref = database.getReference("message");
        itemsRef = database.getReference().child("Items");
        itemsRef.orderByChild("Items");

        if (numItems >= goal){
            for (int x = 0; x < 9; x++){
                itemsRef.removeValue();
            }
        }

        ConstraintLayout constraintLayout;
        constraintLayout = findViewById(R.id.activityCategory);

        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                numItems = snapshot.getChildrenCount();

                int i = 0;

                //-----------------------SETTING CURRENT INFO TO DATABASE INFO

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    translationY += 00;

                    Button btnItem = new Button(CategoryActivity.this);
                    btnItem.setId(i);
                    btnItem.setText(snapshot1.child("Items").child("Items"+i).getKey().toString());
                    btnItem.setTranslationY(translationY);

                    btnItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(CategoryActivity.this, Integer.toString(btnItem.getId()), Toast.LENGTH_SHORT).show();
                            Intent intentProfile = new Intent(CategoryActivity.this, ItemsActivity.class);
                            startActivity(intentProfile);
                        }
                    });

                    btnItem.setTranslationX(translationX);
                    btnItem.setTranslationY(translationY);
                    constraintLayout.addView(btnItem);


                    items[i] = new Items();
                    items[i].Name = snapshot.child("Items").child("Items"+i).child("Name").toString();
                    items[i].Description = snapshot.child("Items").child("Items"+i).child("Description").toString();
                    items[i].Date = snapshot.child("Items").child("Items"+i).child("Description").toString();

                    i++;
                }
                SetLayout();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        btn_Gallery = findViewById(R.id.btn_Gallery);
        btn_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CategoryActivity.this, "btn_Gallery Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(CategoryActivity.this, GalleryActivity.class);
                startActivity(intentProfile);
            }
        });
    }

    public void onClick(View view)
    {
        if (numItems >= goal){
            for (int x = 0; x < 9; x++){
                itemsRef.removeValue();
            }
        }


        //-----------------------UPDATING DATABASE INFO WITH NEW INFO
        if (btnNum < goal){ numItems++;
            itemsRef.push().setValue("Item" + numItems + "");
            progressBar.incrementProgressBy(100/goal);
            ConstraintLayout constraintLayout;
            constraintLayout = findViewById(R.id.activityCategory);
            btnNum++;
            if (translationX < 600){
                translationX += 300;
            }
            else{
                translationX = -200 + 300;
            }
            Button btnItem1 = new Button(CategoryActivity.this);
            btnItem1.setId(btnNum);
            items[(int)numItems - 1] = new Items();
            items[(int)numItems - 1].Name = edtNewName.getText().toString();
            items[(int)numItems - 1].Description = edtNewDescription.getText().toString();
            items[(int)numItems - 1].Date = Calendar.getInstance().getTime().toString();
            btnItem1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(CategoryActivity.this, Integer.toString(btnItem1.getId()), Toast.LENGTH_SHORT).show();
                    Intent intentProfile = new Intent(CategoryActivity.this, ItemsActivity.class);
                    startActivity(intentProfile);
                }
            });
            btnItem1.setTranslationX(translationX);
            btnItem1.setTranslationY(translationY);
            btnItem1.setText(Integer.toString(btnItem1.getId()));
            constraintLayout.addView(btnItem1);


            String itemName = itemsRef.getKey();

            Log.d("--- ", itemName);
            itemsRef.child("Item"+numItems).child("Name"+numItems).push().setValue(edtNewName.getText().toString());
            itemsRef.child("Item"+numItems).child("Description"+numItems).push().setValue(edtNewDescription.getText().toString());
            itemsRef.child("Item"+numItems).child("Goal"+numItems).push().setValue(edtGoal.getText().toString());
        }
        else{
            Toast.makeText(CategoryActivity.this, "Goal Reached!", Toast.LENGTH_SHORT).show();
        }

    }

    void SetLayout(){
        txtNewName = findViewById(R.id.txtNewName);
        txtGoal= findViewById(R.id.txtGoal);;
        txtNewDescription= findViewById(R.id.txtNewDescription);
        edtGoal = findViewById(R.id.edtGoal);
        edtNewName = findViewById(R.id.edtNewName);
        edtNewDescription = findViewById(R.id.edtNewDescription);
        btnAddItems = findViewById(R.id.btnAddItems);
        imageView = findViewById(R.id.imageView);

        txtNewName.setTranslationX(30);
        txtGoal.setTranslationX(30);
        txtNewDescription.setTranslationX(30);
        edtGoal.setTranslationX(550);
        edtNewName.setTranslationX(550);
        edtNewDescription.setTranslationX(30);
        btnAddItems.setTranslationX(550);
        progressBar.setTranslationX(0);

        txtNewName.setTranslationY(300);
        txtGoal.setTranslationY(480);
        txtNewDescription.setTranslationY(20);
        edtGoal.setTranslationY(480);
        edtNewName.setTranslationY(300);
        edtNewDescription.setTranslationY(20);
        btnAddItems.setTranslationY(650);
        progressBar.setTranslationY(800);

        imageView.setLayoutParams(new ConstraintLayout.LayoutParams(5000,5000));
        imageView.setTranslationX(-1300);
        imageView.setTranslationY(-500);

    }



}