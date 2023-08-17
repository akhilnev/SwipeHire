package com.example.user_manager_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.user_manager_v1.helpers.StringHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * The SignInActivity class is responsible for handling user sign-in functionality
 * in the application.
 *
 * @author Aryan Rao
 */
public class SignInActivity extends AppCompatActivity {

    Button sign_in_btn;
    EditText et_email, et_password;
    public static String email = "",choice;

    //public static String useridFindMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Hook Edit Text Fields:
        et_email = findViewById(R.id.email);
        //useridFindMe = et_email.getText().toString();
        et_password = findViewById(R.id.password);
        email = et_email.getText().toString();
        //Log.d("SignInActivity","LOOOOOOKKKKKKKKKKKKKKKKKK ATTTTTTTTTTTTTTTTTTTTTT THISSSSSSSSSSSSSS"+email);

        // Hook Button:
        sign_in_btn = findViewById(R.id.sign_in_btn);

        // Set Sign In Button On Click Listener:
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = et_email.getText().toString();
                SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("email",email);
                editor.apply();
                //Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                //startActivity(intent);
//                finish();
                authenticateUser();
            }
        });
    }
    // End Of On Create Activity.

    public void authenticateUser(){
        // Check For Errors:
        if( !validateEmail() || !validatePassword()){
            return;
        }
        // End Of Check For Errors.

        // Instantiate The Request Queue:
        RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);

        // The URL Posting TO:
        String url = "http://coms-309-017.class.las.iastate.edu:8080/users/login";// make change here according to backend post-mapping

//        // Set Parameters:
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("userid", et_email.getText().toString());
//        params.put("password", et_password.getText().toString());
        JSONObject jsonRequest = new JSONObject();
        try {
            //jsonRequest.put("id", 0);
            jsonRequest.put("userid", et_email.getText().toString());
            jsonRequest.put("password", et_password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Set Request Object:
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                boolean result = Boolean.parseBoolean(response);
                if(response.equals("true")) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    choice = preferences.getString("choice", "");

                    String email = et_email.getText().toString();
                    SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putString("email",email);
                    editor.apply();

                    if(choice.equals("Candidate")) {
                        Intent goToProfile = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(goToProfile);
                        finish();
                    }

                    if(choice.equals("Manager")){
                        Intent goToProfile = new Intent(SignInActivity.this, ManagerMain.class);
                        startActivity(goToProfile);
                        finish();
                    }
                }
                // Handle the boolean response here
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the error here
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public byte[] getBody() {
                return jsonRequest.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        queue.add(request);
    }
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(params),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            // Get Values From Response Object:
//                            boolean valid = (Boolean) response.get("bool");
////                            String first_name = (String) response.get("first_name");
////                            String last_name = (String) response.get("last_name");
////                            String email = (String) response.get("email");
//
//                            // Set Intent Actions:
//                            Intent goToProfile = new Intent(SignInActivity.this, MainActivity.class);
//                            // Pass Values To Profile Activity:
////                            goToProfile.putExtra("first_name", first_name);
////                            goToProfile.putExtra("last_name", last_name);
////                            goToProfile.putExtra("email", email);
//                            // Start Activity:
//                            startActivity(goToProfile);
//                            finish();
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                            System.out.println(e.getMessage());
//                        }
//                        // End Of Try Block.
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                System.out.println(error.getMessage());
//                Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
//            }
//        });// End Of Set Request Object.
//
//        queue.add(jsonObjectRequest);
//
//    }

    public void goToHome(View view){
        Intent intent = new Intent(SignInActivity.this, RaoH.class);
        startActivity(intent);
        finish();
    }
    // End Of Go To Home Intent Method.


    public void goToSigUpAct(View view){
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
    // End Of Go To Sign Up Intent Method.


    public boolean validateEmail(){
        String email = et_email.getText().toString();
        // Check If Email Is Empty:
        if(email.isEmpty()){
            et_email.setError("Email cannot be empty!");
            return false;
        }else if(!StringHelper.regexEmailValidationPattern(email)){
            et_email.setError("Please enter a valid email");
            return false;
        }else{
            et_email.setError(null);
            return true;
        }// Check If Email Is Empty.
    }
    // End Of Validate Email Field.

    public boolean validatePassword() {
        String password = et_password.getText().toString();

        // Check If Password and Confirm Field Is Empty:
        if (password.isEmpty()) {
            et_password.setError("Password cannot be empty!");
            return false;
        } else {
            et_password.setError(null);
            return true;
        }// Check Password and Confirm Field Is Empty.
    }
    // End Of Validate Password;
}
// End Of Sign In Activity Class.