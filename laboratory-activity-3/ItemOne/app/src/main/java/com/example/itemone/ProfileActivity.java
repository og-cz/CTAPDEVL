package com.example.itemone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvEmail, tvBio;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvBio = findViewById(R.id.tvBio);
        btnShare = findViewById(R.id.btnShare);

        // Get Parcelable
        com.example.itemone.UserProfile profile = getIntent().getParcelableExtra("profile");

        if(profile != null){
            tvName.setText("Name: " + profile.getFullName());
            tvEmail.setText("Email: " + profile.getEmail());
            tvBio.setText("Bio: " + profile.getBio());
        }

        // Share button
        btnShare.setOnClickListener(v -> {
            String shareText = "Name: " + profile.getFullName() +
                    "\nEmail: " + profile.getEmail() +
                    "\nBio: " + profile.getBio();

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(shareIntent, "Share Profile"));
        });
    }
}
