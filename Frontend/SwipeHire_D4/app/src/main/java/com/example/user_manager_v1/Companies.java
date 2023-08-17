package com.example.user_manager_v1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

// Companies.java

public class Companies extends AppCompatActivity implements CompanyListAdapter.OnCompanyLikeClickListener {

    private List<Company> companies;

    private RecyclerView companyListRecyclerView;
    private CompanyListAdapter companyListAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comapnies);



        companyListRecyclerView = findViewById(R.id.company_list);
        companyListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the companies list with an empty ArrayList
        companies = new ArrayList<>();

        // Create the CompanyListAdapter with the empty companies list
        companyListAdapter = new CompanyListAdapter(companies, this, this);


        // Set the CompanyListAdapter to the RecyclerView
        companyListRecyclerView.setAdapter(companyListAdapter);


        // Make a GET request to retrieve the list of companies
        String url = "http://coms-309-017.class.las.iastate.edu:8080/Managers/getCompanies";
        RequestQueue queue = Volley.newRequestQueue(Companies.this);
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
