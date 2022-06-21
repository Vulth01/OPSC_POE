package com.example.opsc_poe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    int translationY = 1260, translationX = -200, btnNum = 0, goal = 6;
    EditText edtGoal, edtNewDescription, edtNewName;
    Items[] items;
    ImageButton btn_Gallery;
    ProgressBar progressBar;



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
            Button btnItem1 = new Button(CategoryActivity.this);
            btnItem1.setId(btnNum);
            items[btnNum - 1] = new Items();
            items[btnNum - 1].Name = edtNewName.getText().toString();
            items[btnNum - 1].Description = edtNewDescription.getText().toString();

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


        }
        else{
            Toast.makeText(CategoryActivity.this, "Goal Reached!", Toast.LENGTH_SHORT).show();
        }
    }

}