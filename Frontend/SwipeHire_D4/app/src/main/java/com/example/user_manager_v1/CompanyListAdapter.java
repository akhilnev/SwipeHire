package com.example.user_manager_v1;

import android.app.AlertDialog;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
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
        private List<String> companyDetails;

        public Button info;

        public ViewHolder(View itemView) {
            super(itemView);
            companyNameTextView = itemView.findViewById(R.id.company_name);
            likeButton = itemView.findViewById(R.id.like_button);
            info = itemView.findViewById(R.id.info);

        }
        public void setCompanyDetails(List<String> details) {
            companyDetails = details;
        }
        public void showCompanyDetailsDialog() {
            if (companyDetails != null && !companyDetails.isEmpty()) {
                String companyName = companyDetails.get(0);
                String jobType = companyDetails.get(1);
                String location = companyDetails.get(2);
                String salary = companyDetails.get(3);
                String skillsReq = "";
                if(companyDetails.get(4) != null){
                    skillsReq = companyDetails.get(4);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setTitle("Job Postings");

                // Build a string containing the company details
                StringBuilder sb = new StringBuilder();
                sb.append("Company Name: ").append(companyName).append("\n")
                        .append("Job Type: ").append(jobType).append("\n")
                        .append("Location: ").append(location).append("\n")
                        .append("Salary: ").append(salary).append("\n")
                        .append("Skills Required: ").append(skillsReq);

                builder.setMessage(sb.toString());

                builder.setPositiveButton("OK", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
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

        holder.info.setOnClickListener(v -> {
            try {
                String url = "http://coms-309-017.class.las.iastate.edu:8080/Manager/" + company.getName() + "/getJobDetails";
                RequestQueue queue = Volley.newRequestQueue(context);
                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET, url,
                        response -> {
                            // Extract the company details from the response and set them to the ViewHolder
                            List<String> details = new ArrayList<>(Arrays.asList(response.split("\\$")));

                            holder.setCompanyDetails(details);
                            holder.showCompanyDetailsDialog();
                        },
                        error -> {
                            // Handle the error
                            String errorMessage = error.getMessage();
                            if (errorMessage == null) {
                                errorMessage = "Unknown error";
                            }
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                            Log.e("CompanyListAdapter", "Error getting details for " + company.getName() + ": " + errorMessage);
                        }
                );
                queue.add(stringRequest);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Error getting company details", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return companies.size();
    }


}
