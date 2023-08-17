package com.example.user_manager_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewJobs extends AppCompatActivity {

    TextView tv;

    String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);

        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        description = preferences2.getString("akhisResponse","");


        Intent intent = getIntent();
        String jType = intent.getStringExtra("Job Type: ");
        String sala = intent.getStringExtra("Salary: ");
        String loca = intent.getStringExtra("Location: ");



        tv = findViewById(R.id.jobsview);
        tv.setText("Job Type: "+jType+"\nSalary: "+sala+"\nLocation: "+loca +"\n"+description);

    }
}