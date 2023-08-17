package com.example.user_manager_v1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerSearch extends AppCompatActivity {

    EditText skills;
    ListView listView;

    Button search;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_search);

        skills  = findViewById(R.id.search_bar);
        listView= findViewById(R.id.listviewM);
        search = findViewById(R.id.search_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(ManagerSearch.this);
                String url = "http://coms-309-017.class.las.iastate.edu:8080/Manager/search/" + skills.getText().toString(); // kau url


                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<String> candidates = new ArrayList<>();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                candidates.add(jsonArray.getString(i));
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManagerSearch.this, android.R.layout.simple_list_item_1, candidates);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManagerSearch.this, "JSON exception occurred", Toast.LENGTH_LONG).show();
                        }
                    }


                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println(error.getMessage());
                        Toast.makeText(ManagerSearch.this, "Not getting sent", Toast.LENGTH_LONG).show();
                    }
                }){
                    // Remove getBody() and getBodyContentType() methods
                };

                queue.add(request);

            }
        }); // Add the closing parenthesis here

    }

}



