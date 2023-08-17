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

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.ViewHolder> {

    private List<Company> companies;
    private Context context;
    private OnCompanyLikeClickListener onCompanyLikeClickListener;
    private String full_name;

    Candidate c = new Candidate();

    public interface OnCompanyLikeClickListener {
        void onCompanyLikeClick(Company company);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView companyNameTextView;
        public Button likeButton;

        public ViewHolder(View itemView) {
            super(itemView);
            companyNameTextView = itemView.findViewById(R.id.company_name);
            likeButton = itemView.findViewById(R.id.like_button);
        }
    }

    public CompanyListAdapter(List<Company> companies, Context context, OnCompanyLikeClickListener onCompanyLikeClickListener) {
        this.companies = companies;
        this.context = context;
        this.onCompanyLikeClickListener = onCompanyLikeClickListener;

        // Get the candidate name from SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        full_name = preferences.getString("candidateName", "");
        Log.d("CompanyListAdapter", "Full name from SharedPreferences: " + full_name);
    }

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
