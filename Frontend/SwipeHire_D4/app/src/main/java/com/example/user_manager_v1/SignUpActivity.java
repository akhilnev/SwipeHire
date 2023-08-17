package com.example.user_manager_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.user_manager_v1.helpers.StringHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    EditText full_name, email, password, confirm, information, skills;
    Button sign_up_btn;
    Spinner spinner;
    String roles[] = {"Choose Role","Candidate", "Manager"}, role, choice;
    public static String companyName = "";
    public static boolean isManager = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Hook Edit Text Fields:
        full_name  = findViewById(R.id.full_name);

        email       = findViewById(R.id.email);
        password    = findViewById(R.id.password);
        confirm     = findViewById(R.id.confirm);
        spinner     = findViewById(R.id.spiiner);
        information = findViewById(R.id.information);
        skills      = findViewById(R.id.skills);
        companyName = information.getText().toString();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_spinner_item,roles);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                choice = value;
                Toast.makeText(SignUpActivity.this, value, Toast.LENGTH_SHORT).show();

                if(adapterView.getItemAtPosition(i).equals("Candidate"))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Major", Toast.LENGTH_SHORT).show();
                    skills.setVisibility(View.VISIBLE);
                    role = "major";
                }

                if(adapterView.getItemAtPosition(i).equals("Manager"))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Company", Toast.LENGTH_SHORT).show();
                    skills.setVisibility(View.GONE);
                    role = "companyName";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Hook Sign Up Button:
        sign_up_btn = findViewById(R.id.sign_up_btn);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    processFormFields();
                    String candidateName = full_name.getText().toString();

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("candidateName", candidateName);
                    editor.putString("choice",choice);
                    editor.apply();


                    Log.d("SignUpActivity", "Name saved in SharedPreferences: " + candidateName);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    // End Of On Create Method.



    public void goToHome(View view){
        Intent intent = new Intent(SignUpActivity.this, RaoH.class);
        startActivity(intent);
        finish();
    }

    public void goToSigInAct(View view){
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public void processFormFields() throws JSONException {
        // Check For Errors:
//        if(!validateFirstName() || !validateEmail() || !validatePasswordAndConfirm()){
//            return;
//        }
        // End Of Check For Errors.

        // Instantiate The Request Queue:
        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
        // The URL Posting TO:
        String altUrl1 = "http://coms-309-017.class.las.iastate.edu:8080/candidates/add";// make change here according to backend post-mapping
        String altUrl2 = "http://coms-309-017.class.las.iastate.edu:8080/Managers/add";
        String url = "";

        if(role.equals("major")){
            url = altUrl1;
            isManager = false;
        }else{
            url = altUrl2;
        }

        JSONObject jsonRequest = new JSONObject();
        try {
            //jsonRequest.put("id", 0);
            jsonRequest.put("name", full_name.getText().toString());
            jsonRequest.put("password", password.getText().toString());
            jsonRequest.put("userid", email.getText().toString());
            jsonRequest.put(role, information.getText().toString());
            jsonRequest.put("skills",skills.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // String Request Object:
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("true")){
                    full_name.setText(null);
                    email.setText(null);
                    password.setText(null);
                    confirm.setText(null);
                    information.setText(null);
                    skills.setText(null);


                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    Intent goToProfile = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(goToProfile);
                    finish();
                }
                // End Of Response If Block.

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(SignUpActivity.this, "Registration Un-Successful", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public byte[] getBody() {
                return jsonRequest.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };// End Of String Request Object.

        queue.add(stringRequest);

    }
    // End Of Process Form Fields Method.


//    public boolean validateFirstName(){
//        String fullName = full_name.getText().toString();
//        // Check If First Name Is Empty:
//        if(fullName.isEmpty()){
//            full_name.setError("First name cannot be empty!");
//            return false;
//        }else{
//            full_name.setError(null);
//            return true;
//        }// Check If Full Name Is Empty.
//    }
//    // End Of Validate Full Name Field.
//
//
//
//    public boolean validateEmail(){
//        String email_e = email.getText().toString();
//        // Check If Email Is Empty:
//        if(email_e.isEmpty()){
//            email.setError("Email cannot be empty!");
//            return false;
//        }else if(!StringHelper.regexEmailValidationPattern(email_e)){
//            email.setError("Please enter a valid email");
//            return false;
//        }else{
//            email.setError(null);
//            return true;
//        }// Check If Email Is Empty.
//    }
//    // End Of Validate Email Field.
//
//    public boolean validatePasswordAndConfirm(){
//        String password_p = password.getText().toString();
//        String confirm_p = confirm.getText().toString();
//
//        // Check If Password and Confirm Field Is Empty:
//        if(password_p.isEmpty()){
//            password.setError("Password cannot be empty!");
//            return false;
//        }else if (!password_p.equals(confirm_p)){
//            password.setError("Passwords do not match!");
//            return false;
//        }else if(confirm_p.isEmpty()){
//            confirm.setError("Confirm field cannot be empty!");
//            return false;
//        }else{
//            password.setError(null);
//            confirm.setError(null);
//            return true;
//        }// Check Password and Confirm Field Is Empty.
//    }
    // End Of Validate Password and Confirm Field.



}
// End Of Sign UP Activity Class.