package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class AddReview extends AppCompatActivity {

    private EditText review, process, rating;
    Button submit;
    String myuserid = "";
    String companyName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        review = findViewById(R.id.review);
        process = findViewById(R.id.process);
        rating = findViewById(R.id.rating);
        submit = findViewById(R.id.submitreview);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                myuserid = preferences.getString("email","");

                SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                companyName = preference.getString("companyName","");

                companyName.replace("[", "");
                companyName.replace(" ","");
                companyName.replace("/'","");

                // The URL Posting TO:
                String url = "http://coms-309-017.class.las.iastate.edu:8080/addReviews/"+myuserid+"/"+companyName+"/"+review.getText().toString()+"/"+rating.getText().toString()+"/"+process.getText().toString();

                // Remove the JSON object creation

                RequestQueue queue = Volley.newRequestQueue(AddReview.this);

                // Set Request Object:
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent goToViewer = new Intent(AddReview.this, ViewReview.class);
                        goToViewer.putExtra("Review: ",response);
                        startActivity(goToViewer);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println(error.getMessage());
                        Toast.makeText(AddReview.this, "Not getting sent", Toast.LENGTH_LONG).show();
                    }
                }){

                };

                queue.add(request);
            }
        });
    }

}