package com.example.user_manager_v1;

import android.os.Bundle;
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

/**

 This class represents a list of companies and displays them in a RecyclerView.

 The list of companies is retrieved from a web API and is displayed using a CompanyListAdapter.

 @author Aryan Rao
 */
public class Companies extends AppCompatActivity implements CompanyListAdapter.OnCompanyLikeClickListener {

    private List<Company> companies;
    private RecyclerView companyListRecyclerView;
    private CompanyListAdapter companyListAdapter;

    /**

     This method is called when the activity is created.

     It initializes the RecyclerView and the CompanyListAdapter, retrieves the list of companies from the web API,

     and sets the CompanyListAdapter to the RecyclerView.

     @param savedInstanceState the saved instance state
     */
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
                    String[] companyNames = response.split(",");
                    for (String name : companyNames) {
                        companies.add(new Company(name.trim()));
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

    /**

     This method is called when the user clicks the like button for a company.
     @param company the company that was liked
     */
    @Override
    public void onCompanyLikeClick(Company company) {
        Toast.makeText(this, "Liked " + company.getName(), Toast.LENGTH_SHORT).show();
    }
}