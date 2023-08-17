package com.example.user_manager_v1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
/**
 * This class represents the activity for editing a user's profile.
 * It allows the user to update their profile information such as their name, email, and profile picture.
 * @author  Aryan Rao
 */
public class EditProfile extends AppCompatActivity {
    Button button, back;

    JSONObject setter = ProfileActivity.forDefaults;

    EditText full_name, email, password, confirm, information,bio;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        full_name      = findViewById(R.id.nam);
        email          = findViewById(R.id.eee);
        password       = findViewById(R.id.pass);
        confirm        = findViewById(R.id.conf);
        information    = findViewById(R.id.inf);
        bio            = findViewById(R.id.bioo);
        button         = findViewById(R.id.update);
        back           = findViewById(R.id.buttonn);

        if(setter != null){
            try {
                full_name.setText(setter.getString("name"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            try {
                email.setText(setter.getString("userid"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            try {
                information.setText(setter.getString("major"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            try {
                bio.setText(setter.getString("bio"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    processFormFields();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }


        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    public void goToHome(View view){
        Intent intent = new Intent(EditProfile.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void processFormFields() throws JSONException {
        // Check For Errors:
        if (!validateFirstName() || !validateEmail() || !validatePasswordAndConfirm()) {
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(EditProfile.this);

        String url = "http://coms-309-017.class.las.iastate.edu:8080/candidates/add";// make change here according to backend post-mapping

        JSONObject jsonRequest = new JSONObject();
        try {
            //jsonRequest.put("id", 0);
            jsonRequest.put("name", full_name.getText().toString());
            jsonRequest.put("password", password.getText().toString());
            jsonRequest.put("userid", email.getText().toString());
            jsonRequest.put("major", information.getText().toString());
            jsonRequest.put("bio",bio.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("true")){
                    full_name.setText(null);
                    email.setText(null);
                    password.setText(null);
                    confirm.setText(null);
                    information.setText(null);
                    bio.setText(null);


                    Toast.makeText(EditProfile.this, "Edit Successful", Toast.LENGTH_LONG).show();
                    Intent goToProfile = new Intent(EditProfile.this, MainActivity.class);
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
                Toast.makeText(EditProfile.this, "Edit Un-Successful", Toast.LENGTH_LONG).show();
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


    public boolean validateFirstName(){
        String fullName = full_name.getText().toString();
        // Check If First Name Is Empty:
        if(fullName.isEmpty()){
            full_name.setError("First name cannot be empty!");
            return false;
        }else{
            full_name.setError(null);
            return true;
        }// Check If Full Name Is Empty.
    }
    // End Of Validate Full Name Field.



    public boolean validateEmail(){
        String email_e = email.getText().toString();
        // Check If Email Is Empty:
        if(email_e.isEmpty()){
            email.setError("Email cannot be empty!");
            return false;
        }else if(!StringHelper.regexEmailValidationPattern(email_e)){
            email.setError("Please enter a valid email");
            return false;
        }else{
            email.setError(null);
            return true;
        }// Check If Email Is Empty.
    }
    // End Of Validate Email Field.

    public boolean validatePasswordAndConfirm(){
        String password_p = password.getText().toString();
        String confirm_p = confirm.getText().toString();

        // Check If Password and Confirm Field Is Empty:
        if(password_p.isEmpty()){
            password.setError("Password cannot be empty!");
            return false;
        }else if (!password_p.equals(confirm_p)){
            password.setError("Passwords do not match!");
            return false;
        }else if(confirm_p.isEmpty()){
            confirm.setError("Confirm field cannot be empty!");
            return false;
        }else{
            password.setError(null);
            confirm.setError(null);
            return true;
        }// Check Password and Confirm Field Is Empty.
    }
}
