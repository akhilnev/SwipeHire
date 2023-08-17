package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewReview extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review);

        Intent intent = getIntent();
        String resume = intent.getStringExtra("Review: ");

        tv = findViewById(R.id.viewreview);
        tv.setText(resume);
    }
}