package com.example.itemthree;


import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnPick, btnClear;
    Uri imageUri = null;

    // Activity Result API launcher
    ActivityResultLauncher<String> pickImageLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        btnPick = findViewById(R.id.btnPick);
        btnClear = findViewById(R.id.btnClear);

        // Setup Activity Result launcher
        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if(uri != null){
                        imageUri = uri;
                        imageView.setImageURI(uri);
                    }
                }
        );

        // Pick image button
        btnPick.setOnClickListener(v -> pickImageLauncher.launch("image/*"));

        // Clear image button
        btnClear.setOnClickListener(v -> {
            imageUri = null;
            imageView.setImageDrawable(null);
        });

        // Restore image after rotation
        if(savedInstanceState != null){
            imageUri = savedInstanceState.getParcelable("imageUri");
            if(imageUri != null){
                imageView.setImageURI(imageUri);
            }
        }
    }

    // Save image URI across configuration changes
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("imageUri", imageUri);
    }
}
