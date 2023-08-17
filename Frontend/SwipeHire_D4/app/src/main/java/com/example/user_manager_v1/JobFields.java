package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import org.json.JSONException;
import org.json.JSONObject;

public class JobFields extends AppCompatActivity {

    EditText jobType, salary, location,rskills;
    Button submit;
    String myuserid = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_fields);

        jobType = findViewById(R.id.jobtype);
        salary = findViewById(R.id.salary);
        location = findViewById(R.id.location);
        submit = findViewById(R.id.submitbutton);
        rskills = findViewById(R.id.reqskills);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                myuserid = preferences.getString("email","");

                // The URL Posting TO:
                String url = "http://coms-309-017.class.las.iastate.edu:8080/Manager/" + myuserid + "/addjob/" + jobType.getText().toString() + "/" + location.getText().toString() + "/" + salary.getText().toString() + "/" + rskills.getText().toString();

                // Remove the JSON object creation

                RequestQueue queue = Volley.newRequestQueue(JobFields.this);

                // Set Request Object:
                StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String job = jobType.getText().toString();
                        String loc = location.getText().toString();
                        String sal = salary.getText().toString();
                        Intent goToViewer = new Intent(JobFields.this, ViewJobs.class);
                        goToViewer.putExtra("Job Type: ", job);
                        goToViewer.putExtra("Salary: ", sal);
                        goToViewer.putExtra("Location: ", loc);
                        startActivity(goToViewer);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println(error.getMessage());
                        Toast.makeText(JobFields.this, "Not getting sent", Toast.LENGTH_LONG).show();
                    }
                }){

                };

                queue.add(request);
            }
        });
    }
}