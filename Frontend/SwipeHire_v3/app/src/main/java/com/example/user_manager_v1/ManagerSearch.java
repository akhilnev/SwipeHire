package com.example.user_manager_v1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**

 ManagerSearch is an Activity that displays a list of candidates and allows the user to search for

 candidates based on their bio information. The list of candidates is retrieved from a REST API

 and displayed using a RecyclerView.

 @author Aryan Rao
 */
public class ManagerSearch extends AppCompatActivity {

    private List<Candidate> candidates;
    private List<Candidate> filteredCandidates;
    private RecyclerView candidateListRecyclerView;
    private CandidateListAdapter candidateListAdapter;

    /**

     This method is called when the activity is created. It initializes the RecyclerView and adapter,

     makes a GET request to retrieve the list of candidates with their bio information from a REST API,

     initializes the search bar and button, and sets an OnClickListener on the search button to filter

     the list of candidates.

     @param savedInstanceState The saved instance state of the activity
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_search);

// Initialize the RecyclerView and adapter
        candidateListRecyclerView = findViewById(R.id.candidate_list);
        candidateListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        candidateListAdapter = new CandidateListAdapter(this, candidates);
        candidateListRecyclerView.setAdapter(candidateListAdapter);

// Make a GET request to retrieve the list of candidates with their bio information
        String url = "http://coms-309-017.class.las.iastate.edu:8080/candidates";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
// Parse the JSON response into a list of candidates with their bio information
                    candidates = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject candidateJson = response.getJSONObject(i);
                            String name = candidateJson.getString("name");
                            String bio = candidateJson.getString("bio");
                            candidates.add(new Candidate(name, bio));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    // Update the adapter with the list of candidates
                    candidateListAdapter.setCandidates(candidates);
                    candidateListAdapter.notifyDataSetChanged();
                },
                error -> {
                    // Handle the error
                    Toast.makeText(this, "Error retrieving candidates: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );
        queue.add(jsonArrayRequest);

// Initialize the filtered candidates list
        filteredCandidates = new ArrayList<>();

// Initialize the search bar and button
        EditText searchEditText = findViewById(R.id.search_bar);
        Button searchButton = findViewById(R.id.search_button);

// Set an OnClickListener on the search button to filter the list of candidates
        searchButton.setOnClickListener(v -> {
            String searchQuery = searchEditText.getText().toString().toLowerCase();
            // Filter the list of candidates based on their bio information
            filteredCandidates.clear();
            for (Candidate candidate : candidates) {
                if (candidate.getBio().toLowerCase().contains(searchQuery)) {
                    filteredCandidates.add(candidate);
                }
            }

            // Update the adapter with the filtered list of candidates
            candidateListAdapter.setCandidates(filteredCandidates);
            candidateListAdapter.notifyDataSetChanged();
        });
    }
}
