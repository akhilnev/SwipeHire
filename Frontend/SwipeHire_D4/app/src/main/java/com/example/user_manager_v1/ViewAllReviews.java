package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



public class ViewAllReviews extends AppCompatActivity {

    String allReviews = "";
    TextView tv;
    String companyName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_reviews);

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        companyName = preference.getString("companyName","");

        String url = "http://coms-309-017.class.las.iastate.edu:8080/getReviews/"+companyName;
        RequestQueue queue = Volley.newRequestQueue(ViewAllReviews.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                response -> {
                    // Parse the String response into a list of companies
                    tv = findViewById(R.id.allreviews);
                    tv.setText(response);
                },
                error -> {
                    // Handle the error
                    Toast.makeText(this, "Error retrieving reviews: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );
        queue.add(stringRequest);

//        tv = findViewById(R.id.allreviews);
//        String hello = "hello";
//        tv.setText(hello);

    }
}