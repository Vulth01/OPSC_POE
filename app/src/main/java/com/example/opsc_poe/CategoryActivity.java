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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    //Button btnAddItems;
    //EditText edtGoal;
    //ImageView imgView1, imgView2, imgView3, imgView4, imgView5, imgView6;
    //Category category = new Category();
    //ImageView[] imageViews;
    //int imgIndex = 0;
    //Image image;

    int translationY = 1200, translationX = -200, btnNum = 0, goal = 6;
    EditText edtGoal, edtNewDescription, edtNewName;
    Items[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        edtGoal = findViewById(R.id.edtGoal);
        edtGoal.setText("9");
        goal = Integer.parseInt(edtGoal.getText().toString());
        items = new Items[goal];


        edtNewDescription = findViewById(R.id.edtNewDescription);
        edtNewName = findViewById(R.id.edtNewName);


    }

    public void onClick(View view)
    {
        if (btnNum < goal){

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









/*

        goal = Integer.parseInt(edtGoal.getText().toString());

        imgView1.findViewById(R.id.imageView1);
        btnAddItems.findViewById(R.id.btnAddItems);
        imageViews = new ImageView[goal];

        btnAddItems.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });





        LayoutInflater myInflater = getLayoutInflater();
        View newView = myInflater.inflate(R.layout.activity_category, null);

        EditText etName = (EditText) newView.findViewById(R.id.editTextTextPersonName);
        etName.setText("Hello!");

        //Display layout permanently
        LinearLayout newLayout = findViewById(R.id.linearlayout);
        newLayout.addView(newView);

        imgView1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ImageView img = new ImageView(CategoryActivity.this); //put activity name here
                //imageViews[imgIndex] = img;
                //imgIndex++;
                Toast.makeText(CategoryActivity.this, "brr", Toast.LENGTH_SHORT).show();
            }
        });


        List<Items> items = null;

        category.CatItems = new Items[5];

        for (int i = 0; i < category.CatItems.length; i++){
            category.CatItems[i].Name = "";
            category.CatItems[i].ItemImage = image;
        }


        imgView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CategoryActivity.this, "btn_Gallery Pressed", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(CategoryActivity.this, ItemsActivity.class);
                startActivity(intentProfile);
            }
        });
*/
