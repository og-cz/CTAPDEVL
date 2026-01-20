package com.example.itemtwo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvName, tvPrice, tvDesc;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        tvDesc = findViewById(R.id.tvDesc);
        img = findViewById(R.id.imgThumbnail);

        Product p = getIntent().getParcelableExtra("product");
        if(p != null){
            tvName.setText(p.name);
            tvPrice.setText(p.price);
            tvDesc.setText(p.description);
            img.setImageResource(p.imageResId);
        }
    }
}
