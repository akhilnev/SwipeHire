package com.example.companylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] companyNames = {"Amazon", "Apple", "Google", "Meta", "Microsoft", "Netflix", "Tesla"};
    int[] companyImages = {R.drawable.amazon, R.drawable.apple, R.drawable.google, R.drawable.meta, R.drawable.microsoft, R.drawable.netflix, R.drawable.tesla};
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, companyNames, companyImages));
    }
}