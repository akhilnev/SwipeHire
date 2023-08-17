package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class RecommendJobs extends AppCompatActivity implements CompanyListAdapter.OnCompanyLikeClickListener{

    private List<Company> companies;
    String myuserid = "";
    private RecyclerView companyListRecyclerView;
    private CompanyListAdapter companyListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_jobs);

        companyListRecyclerView = findViewById(R.id.company_list);
        companyListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myuserid = SignInActivity.email;

        // Initialize the companies list with an empty ArrayList
        companies = new ArrayList<>();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        myuserid = preferences.getString("email","");

        // Create the CompanyListAdapter with the empty companies list
        companyListAdapter = new CompanyListAdapter(companies, this, this);


        // Set the CompanyListAdapter to the RecyclerView
        companyListRecyclerView.setAdapter(companyListAdapter);

        // Make a GET request to retrieve the list of companies
        String url = "http://coms-309-017.class.las.iastate.edu:8080/candidates/" + myuserid + "/recommend/";//url
        RequestQueue queue = Volley.newRequestQueue(RecommendJobs.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                response -> {
                    // Parse the String response into a list of companies
                    String[] companyNames = response.substring(1, response.length()-1).split(",");
                    for(int i = 0; i < companyNames.length; i++){
                        companyNames[i] = companyNames[i].replace("\"", "");
                    }
                    for (String name : companyNames) {
                        companies.add(new Company(name));
                    }

                    // Update the adapter with the list of companies
                    companyListAdapter.notifyDataSetChanged();
                },
                error -> {
                    // Handle the error
                    Toast.makeText(this, "Error retrieving companies: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );
        queue.add(stringRequest);
    }

    @Override
    public void onCompanyLikeClick(Company company) {
        Toast.makeText(this, "Liked " + company.getName(), Toast.LENGTH_SHORT).show();
    }
}