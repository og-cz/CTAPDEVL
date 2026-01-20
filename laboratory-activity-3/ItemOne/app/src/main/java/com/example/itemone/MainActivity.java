package com.example.itemone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etEmail, etBio;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextText2);
        etEmail = findViewById(R.id.editTextTextEmailAddress);
        etBio = findViewById(R.id.editTextText6);
        btnSubmit = findViewById(R.id.button);

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String bio = etBio.getText().toString().trim();

            if(name.isEmpty() || email.isEmpty() || bio.isEmpty()){
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!email.contains("@")){
                Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(bio.length() > 140){
                Toast.makeText(this, "Bio must be max 140 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            UserProfile userProfile = new UserProfile(name, email, bio);

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("profile", userProfile);
            startActivity(intent);
        });
    }
}
