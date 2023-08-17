package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user_manager_v1.R;

public class ViewResume extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resume);

        Intent intent = getIntent();
        String resume = intent.getStringExtra("Resume: ");

        tv = findViewById(R.id.resumeview);
        tv.setText(resume);

    }
}