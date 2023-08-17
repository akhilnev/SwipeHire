package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SearchCompany extends AppCompatActivity {

    EditText job, location, salary;
    Button search,back;

    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_company);

        job          = findViewById(R.id.job);
        location     = findViewById(R.id.location);
        salary       = findViewById(R.id.salary);
        search       = findViewById(R.id.srch);
        listView     = findViewById(R.id.list_view);
        back         = findViewById(R.id.bckkk);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(SearchCompany.this);


                String j = job.getText().toString().trim();
                String l = location.getText().toString().trim();
                String s = salary.getText().toString().trim();

                if (j.isEmpty()) {
                    j = "NA";
                }

                if (l.isEmpty()) {
                    l = "NA";
                }

                if (s.isEmpty()) {
                    s = "NA";
                }

                String url = String.format("http://coms-309-017.class.las.iastate.edu:8080/Managers/getCompanyByJobLocationSalary/%s/%s/%s", j, l, s);

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<String> companies = new ArrayList<>();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                companies.add(jsonArray.getString(i));
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchCompany.this, android.R.layout.simple_list_item_1, companies);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SearchCompany.this, "JSON exception occurred", Toast.LENGTH_LONG).show();
                        }
                    }


                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println(error.getMessage());
                        Toast.makeText(SearchCompany.this, "Not getting sent", Toast.LENGTH_LONG).show();
                    }
                }){
                    // Remove getBody() and getBodyContentType() methods
                };

                queue.add(request);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCompany.this, SearchClass.class);
                startActivity(intent);
                finish();
            }
        });
    }
}