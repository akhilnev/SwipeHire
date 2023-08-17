package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class UploadResume extends AppCompatActivity {

    private EditText workExperience;
    private EditText projects;
    private EditText awardsAndAccomplishments;
    private EditText leadershipAndExtra;
    private Button buttonSubmit;

    String myuserid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_resume);

        workExperience = findViewById(R.id.editTextWorkExperience);
        projects = findViewById(R.id.editTextProjects);
        awardsAndAccomplishments = findViewById(R.id.editTextAwards);
        leadershipAndExtra = findViewById(R.id.editTextLeadership);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                myuserid = preferences.getString("email","");

                // The URL Posting TO:
                String url = "http://coms-309-017.class.las.iastate.edu:8080/candidates/updateResume/"+myuserid +"/"+workExperience.getText().toString()+"/"+projects.getText().toString()+"/"+awardsAndAccomplishments.getText().toString()+"/"+leadershipAndExtra.getText().toString();

                // Remove the JSON object creation

                RequestQueue queue = Volley.newRequestQueue(UploadResume.this);

                // Set Request Object:
                StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent goToViewer = new Intent(UploadResume.this, ViewResume.class);
                        goToViewer.putExtra("Resume: ",response);
                        startActivity(goToViewer);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println(error.getMessage());
                        Toast.makeText(UploadResume.this, "Not getting sent", Toast.LENGTH_LONG).show();
                    }
                }){
                    // Remove getBody() and getBodyContentType() methods
                };

                queue.add(request);

            }
        });
    }

//    public void sendToAkhi(){
//
//
//    }
}