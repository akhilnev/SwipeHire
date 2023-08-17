package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

/**
 * This class represents the activity for viewing job information.
 * @author - Hrishikesha Kyathsandra
 */
public class ViewJobs extends AppCompatActivity {

    /** The text view for displaying the job information */
    TextView tv;

    /** The job description retrieved from the backend */
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



//package com.example.user_manager_v1;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.widget.TextView;
//
//public class ViewJobs extends AppCompatActivity {
//
//    TextView tv;
//
//    String description = "";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_jobs);
//
//        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        description = preferences2.getString("akhisResponse","");
//
//
//        Intent intent = getIntent();
//        String jType = intent.getStringExtra("Job Type: ");
//        String sala = intent.getStringExtra("Salary: ");
//        String loca = intent.getStringExtra("Location: ");
//
//
//
//        tv = findViewById(R.id.jobsview);
//        tv.setText("Job Type: "+jType+"\nSalary: "+sala+"\nLocation: "+loca +"\n"+description);
//
//    }
//}