package com.example.user_manager_v1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**

 This class displays the user's profile information retrieved from the API.

 The user's name, email, and major are extracted from a JSON response and displayed.

 @author Aryan Rao
 */
public class ViewProfile extends AppCompatActivity {

    Button button;
    EditText namev, emailv, majorv;

    /**
     * This method is called when the activity is first created. It initializes the views and
     * sends a GET request to the API to retrieve the user's profile information.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        button = findViewById(R.id.view_profile);

        RequestQueue queue = Volley.newRequestQueue(ViewProfile.this);

        String url = "http://coms-309-017.class.las.iastate.edu:8080/candidates/";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Extract the user's initial details from the JSON response
                            String name = response.getString("name");
                            String email = response.getString("userid");
                            String major = response.getString("major");


                            namev.setText(name);
                            emailv.setText(email);
                            majorv.setText(major);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the error
            }
        });
// Add the request to the RequestQueue.
        queue.add(jsonRequest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProfile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
