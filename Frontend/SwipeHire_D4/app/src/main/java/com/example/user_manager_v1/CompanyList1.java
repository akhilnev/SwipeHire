package com.example.user_manager_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class CompanyList1 extends AppCompatActivity {

    String companyName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list1);

        ArrayList<String> akhiList = new ArrayList<>();
        ListView listView = findViewById(R.id.listcompanies);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.row,akhiList);
        listView.setAdapter(arrayAdapter);

        // Make a GET request to retrieve the list of companies
        String url = "http://coms-309-017.class.las.iastate.edu:8080/Managers/getCompanies";
        RequestQueue queue = Volley.newRequestQueue(CompanyList1.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                response -> {
                    // Parse the String response into a list of companies
                    String[] companyNames = response.split(",");
                    for (String name : companyNames) {
                       name =  name.replace(" ", "");
                       name =  name.replace("\"", "");
                       name =  name.trim();

                        akhiList.add(name.replace("[",""));
                    }

                    // Update the adapter with the list of companies
                    arrayAdapter.notifyDataSetChanged();
                },
                error -> {
                    // Handle the error
                    Toast.makeText(this, "Error retrieving companies: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );
        queue.add(stringRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //put it in a for loop using the list akhilesh sends u.
                for(int i = 0; i < akhiList.size(); i++){
                    if(position == i){
                        companyName = akhiList.get(i);
                        SharedPreferences preferences5 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = preferences5.edit();
                        editor.putString("companyName",companyName);
                        editor.apply();
                        startActivity(new Intent(CompanyList1.this, AddReview.class));
                    }
                }
            }

        });

        // Initialize the SearchView
        SearchView searchView = findViewById(R.id.searchcompanies);

// Add a listener to the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the list based on the user's input
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }
}