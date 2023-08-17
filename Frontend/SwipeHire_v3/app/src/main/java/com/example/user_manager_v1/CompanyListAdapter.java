package com.example.user_manager_v1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Adapter class for displaying a list of companies in a RecyclerView.
 * Provides a way to listen for like button clicks on each item in the list.
 * Uses SharedPreferences to retrieve the name of the currently logged in candidate.
 * Uses Volley to send a POST request to an API endpoint when the like button is clicked.
 * @author Aryan Rao
 */
public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.ViewHolder> {

    private List<Company> companies; // List of companies to display
    private Context context; // Context of the activity or fragment that the RecyclerView is in
    private OnCompanyLikeClickListener onCompanyLikeClickListener; // Listener for like button clicks
    private String full_name; // Full name of the currently logged in candidate

    // Candidate object used to access SharedPreferences
    Candidate c = new Candidate();

    /**
     * Interface for listening to like button clicks on each item in the list.
     */
    public interface OnCompanyLikeClickListener {
        void onCompanyLikeClick(Company company);
    }

    /**
     * ViewHolder class for holding views of each item in the list.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView companyNameTextView; // TextView for displaying the company name
        public Button likeButton; // Button for liking the company

        public ViewHolder(View itemView) {
            super(itemView);
            companyNameTextView = itemView.findViewById(R.id.company_name);
            likeButton = itemView.findViewById(R.id.like_button);
        }
    }

    /**
     * Constructor for CompanyListAdapter.
     * Retrieves the name of the currently logged in candidate from SharedPreferences.
     * @param companies List of companies to display
     * @param context Context of the activity or fragment that the RecyclerView is in
     * @param onCompanyLikeClickListener Listener for like button clicks
     */
    public CompanyListAdapter(List<Company> companies, Context context, OnCompanyLikeClickListener onCompanyLikeClickListener) {
        this.companies = companies;
        this.context = context;
        this.onCompanyLikeClickListener = onCompanyLikeClickListener;

        // Get the candidate name from SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        full_name = preferences.getString("candidateName", "");
        Log.d("CompanyListAdapter", "Full name from SharedPreferences: " + full_name);
    }

    /**
     * Creates a new ViewHolder for each item in the list.
     * Sets an OnClickListener for the like button on each item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.likeButton.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            Company company = companies.get(position);
            onCompanyLikeClickListener.onCompanyLikeClick(company);
        });
        return viewHolder;
    }

    /**
     * Binds the data of the given position to the views in the ViewHolder.
     * Sets an OnClickListener for the like button.
     * @param holder The ViewHolder to bind data to
     * @param position The position of the data in the list
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Company company = companies.get(position);

        holder.companyNameTextView.setText(company.getName());

        holder.likeButton.setOnClickListener(v -> {
            try {
                // Create the JSON object to send to the API endpoint
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("candidateName", full_name);
                jsonObject.put("companyName", company.getName());

                // Send the JSON object to the API endpoint
                String url = "http://coms-309-017.class.las.iastate.edu:8080/apcandidate/add";
                RequestQueue queue = Volley.newRequestQueue(context);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, url, jsonObject,
                        response -> {
                            // Handle the response
                            Log.d("CompanyListAdapter", "Server response: " + response.toString());
                            Toast.makeText(context, "Liked " + company.getName(), Toast.LENGTH_SHORT).show();
                        },
                        error -> {
                            // Handle the error
                            String errorMessage = error.getMessage();
                            if (errorMessage == null) {
                                errorMessage = "Unknown error";
                            }
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                            Log.e("CompanyListAdapter", "Error liking " + company.getName() + ": " + errorMessage);
                        }
                );
                queue.add(jsonObjectRequest);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error creating JSON object", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return companies.size();
    }


}
