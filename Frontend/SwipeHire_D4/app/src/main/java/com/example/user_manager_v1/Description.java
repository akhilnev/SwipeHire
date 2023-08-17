package com.example.user_manager_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Description extends AppCompatActivity {

    Button btn;
    /*
    Sending to Akhi
     */

    String userid = "";
    String description = "";

    /*
    Storing from Akhi
     */
   // String akhisResponse = "";
    EditText editText;

    String localDesc = "";

    public static String storeDesc = ""; //stores backend response

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        editText = findViewById(R.id.descid);


        btn = findViewById(R.id.description);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToAkhi();
                Intent intent = new Intent(Description.this, ListViewer.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void sendToAkhi(){


        String typed = editText.getText().toString();
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences1.edit();
        editor.putString("typed",typed);
        editor.apply();

//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        userid = preferences.getString("email","");



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        description = preferences.getString("typed","");

        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userid = preferences2.getString("email","");

        String url = "http://coms-309-017.class.las.iastate.edu:8080/Managers/"+description+"/"+userid;

        RequestQueue queue = Volley.newRequestQueue(Description.this);

        // Set Request Object:
        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String akhisResponse = response;
                SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("akhisResponse",akhisResponse);
                editor.apply();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(Description.this, "Not getting sent", Toast.LENGTH_LONG).show();
            }
        }){
            // Remove getBody() and getBodyContentType() methods
        };

        queue.add(request);
    }

   // using Retrofit
//    public void sendToAkhi(){
//        //new retrofit object
//        String userId = SignInActivity.userid;
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://coms-309-017.class.las.iastate.edu:8080/Managers/Description/"+userId+"/"+desc)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//
//        MyApi api = retrofit.create(MyApi.class); // instanciating the interface
//
//        String myString = desc;
//        Call<String> call = api.sendStringToServer(myString);
//
//        call.enqueue(new Callback<String>() {
//
//            @Override
//            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
//                if (response.isSuccessful()) {
//                    storeDesc = response.body(); // storing his response
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                // Handle any errors here
//            }
//        });
//
//    }

    //using volley
//    public void sendToAkhi(){
//        RequestQueue queue = Volley.newRequestQueue(Description.this);
//        String companyName = SignUpActivity.companyName;
//        String userId = SignInActivity.userid;
//        String url = "http://coms-309-017.class.las.iastate.edu:8080/Managers/Description/"+userId+"/"+desc; // Akhis backend url
//
//        String requestBody = desc;  // The string I want to send to Akhi
//
//        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, // new String Request object
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Handle response
//                        storeDesc = response; // Storing Akhis response in this string.
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                System.out.println(error.getMessage());
//            }
//        }) {
//
//            public byte[] getBody() throws AuthFailureError {
//                return requestBody.getBytes();
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "application/String";
//            }
//        };
//
//        queue.add(stringRequest);
//    }

    //This is the one we were trying before the demo which didnt work.
//    public void sendToAkhi() {
//        // Instantiate The Request Queue:
//
//
//        // The URL Posting TO:
//        //String companyName = SignUpActivity.companyName;
//        String userId = SignInActivity.userid;
//        String url = "http://coms-309-017.class.las.iastate.edu:8080/Managers/Description/"+userId+"/"+desc;
////        JSONObject jsonRequest = new JSONObject();
////        try {
////            //jsonRequest.put("id", 0);
////            jsonRequest.put("companyDescription", desc); //whatever hrishi is putting
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//
//        // Set Request Object:
//        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                boolean result = Boolean.parseBoolean(response);
//
//                    //Intent goToProfile = new Intent(Description.this, ListViewer.class);
//                    //startActivity(goToProfile);
//                    //finish();
//                storeDesc = response;
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // Handle the error here
//                error.printStackTrace();
//                System.out.println(error.getMessage());
//                //Toast.makeText(Description.this, "Heres the desc", Toast.LENGTH_LONG).show();
//            }
//        }){
//
//            @Override
//            public String getBodyContentType() {
//                return "application/string";
//            }
//        };
//        RequestQueue queue = Volley.newRequestQueue(Description.this);
//        queue.add(request);
//    }
 }
