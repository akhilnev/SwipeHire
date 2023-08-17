package com.example.as2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Spinner spMethod;
    EditText etUrl;
    EditText etRequest;
    TextView tvResponse;
    Button btnSend;
    Button btnImage;

    private String method;
    private String url = "https://jsonplaceholder.typicode.com/users/1";
    private String requestBody;
    private String responseBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // rest
        etUrl = findViewById(R.id.etUrl);
        etRequest = findViewById(R.id.etRequest);
        tvResponse = findViewById(R.id.tvResponse);
        btnSend = findViewById(R.id.sendBtn);
        btnImage = findViewById(R.id.imgBtn);

        etUrl.setText(url);

        // method spinner
        Spinner spMethod = findViewById(R.id.spMethod);
        String[] methods = new String[]{"GET", "POST"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, methods);
        spMethod.setAdapter(adapter);
        spMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                method = (String) parent.getItemAtPosition(position);
                if (method.equals("GET")) etRequest.setText("Leave this field empty");
                else etRequest.setText("Enter JSON object here");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                method = "GET";
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                url = etUrl.getText().toString();
                requestBody = etRequest.getText().toString();
                if (method.equals("GET")) getRequest();
                else postRequest();
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getRequest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    // String response can be converted to JSONObject via
                    // JSONObject object = new JSONObject(response);
                    tvResponse.setText("Response is: "+ response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    tvResponse.setText("That didn't work!" + error.toString());
                }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        Toast.makeText(getApplicationContext(),method+" request sent!",Toast.LENGTH_SHORT).show();
    }

    private void postRequest() {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // Convert input to JSONObject
        JSONObject body = null;
        try{
            // etRequest should contain a JSON object string as your POST body
            // similar to what you would have in POSTMAN-body field
            // and the fields should match with the object structure of @RequestBody on sb
            body = new JSONObject(etRequest.getText().toString());
        } catch (Exception e){
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        tvResponse.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request); // send request
    }

}