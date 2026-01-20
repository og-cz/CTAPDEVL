package com.example.itemtwo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText etSearch;
    TextView tvEmpty;
    ProductAdapter adapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        etSearch = findViewById(R.id.etSearch);
        tvEmpty = findViewById(R.id.tvEmpty);

        productList = new ArrayList<>();
        productList.add(new Product("Apple", "$1.00", "Fresh red apple", R.drawable.ic_launcher_foreground));
        productList.add(new Product("Banana", "$0.50", "Yellow banana", R.drawable.ic_launcher_foreground));
        productList.add(new Product("Orange", "$0.80", "Juicy orange", R.drawable.ic_launcher_foreground));
        productList.add(new Product("Mango", "$1.50", "Sweet mango", R.drawable.ic_launcher_foreground));
        productList.add(new Product("Grapes", "$2.00", "Green grapes", R.drawable.ic_launcher_foreground));
        productList.add(new Product("Pineapple", "$3.00", "Tropical pineapple", R.drawable.ic_launcher_foreground));

        adapter = new ProductAdapter(MainActivity.this, productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Search filter
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().toLowerCase();
                List<Product> filtered = new ArrayList<>();
                for(Product p : productList){
                    if(p.name.toLowerCase().contains(query)){
                        filtered.add(p);
                    }
                }
                adapter.filterList(filtered); // Update existing adapter
                tvEmpty.setVisibility(filtered.size() == 0 ? TextView.VISIBLE : TextView.GONE);
            }

        });
    }
}
