package com.example.opsc_poe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemsActivity extends AppCompatActivity {

    TextView edtName, edtDescription, edtDate;
    Items[] items = new Items[9];
    public static final int CAMERA_ACTION_CODE = 1;
    ImageView imageProfile;
    Button takePhoto;

    ImageView imageView7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        edtName = findViewById(R.id.txtName);
        edtDescription = findViewById(R.id.txtDescription);
        edtDate = findViewById(R.id.txtDate);
        imageView7 = findViewById(R.id.imageView7);

    }



    public void onPhotoTaken(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Check if the device/emulator supports the above action (To prevent crashing)
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, CAMERA_ACTION_CODE);
        }
        else
        {
            Toast.makeText(ItemsActivity.this, "No app to support this", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_ACTION_CODE && resultCode == RESULT_OK && data != null)
        {
            //We want to get the intent data
            Bundle bundle = data.getExtras();
            Bitmap finalPhoto = (Bitmap) bundle.get("data"); //gives us a thumbnail
            imageView7.setImageBitmap(finalPhoto);
        }
    }

}