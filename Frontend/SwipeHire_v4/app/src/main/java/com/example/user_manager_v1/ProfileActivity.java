package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    public static JSONObject forDefaults;
    Button sign_out_btn,v_button, e_button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        v_button = findViewById(R.id.view_profile);
        e_button = findViewById(R.id.edit_profile);
        sign_out_btn = findViewById(R.id.sign_out_btn);

        v_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToHome = new Intent(ProfileActivity.this, ViewProfile.class);
                startActivity(goToHome);
                finish();
            }
        });

        e_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setDefault();
                Intent goToHome = new Intent(ProfileActivity.this, EditProfile.class);
                startActivity(goToHome);
                finish();
            }
        });


        sign_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUserOut();
            }
        });
    }

    private void setDefault() {

        RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);

        String url = "http://coms-309-017.class.las.iastate.edu:8080/candidates/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        forDefaults = response;
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle the error
                    }
                });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);



    }

    public void signUserOut(){
        Intent goToHome = new Intent(ProfileActivity.this, RaoH.class);
        startActivity(goToHome);
        finish();

    }
}