package com.example.user_manager_v1;

import android.content.Intent;
import android.os.Bundle;
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

public class ListViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_viewer);

        ArrayList<String> akhiList = new ArrayList<>();
        ListView listView = findViewById(R.id.listview);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.row,akhiList);
        listView.setAdapter(arrayAdapter);

        // Make a GET request to retrieve the list of companies
        String url = "http://coms-309-017.class.las.iastate.edu:8080/Managers/getCompanies";
        RequestQueue queue = Volley.newRequestQueue(ListViewer.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                response -> {
                    // Parse the String response into a list of companies
                    String[] companyNames = response.split(",");
                    for (String name : companyNames) {
                        akhiList.add(name);
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
                        startActivity(new Intent(ListViewer.this, CompanyData.class));
                    }
                }
//                if(position==0){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
//                else if(position == 1){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
//
//                else if(position ==2){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
//
//                else if(position ==3){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
//                else if(position ==4){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
//                else if(position ==5){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
//                else if(position ==6){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
//                else if(position ==7){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
//                else if(position ==3){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
//                else if(position ==3){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
//                else if(position ==3){
//                    startActivity(new Intent(ListViewer.this, CompanyData.class));
//                }
            }

        });

        // Initialize the SearchView
        SearchView searchView = findViewById(R.id.searchView);

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