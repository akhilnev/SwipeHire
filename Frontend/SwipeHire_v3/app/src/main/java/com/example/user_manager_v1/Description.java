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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


/**

 This activity allows the user to enter and save a description for their profile
 @author - Hrishikesha Kyathsandra
 */
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
    EditText editText;

    String localDesc = "";

    public static String storeDesc = ""; //stores backend response

    /**
     * This method is called when the activity is created. It initializes the UI and sets up the
     * onClickListener for the save button.
     * @param savedInstanceState The previously saved state of the activity
     */
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

    /**
     * This method sends the user's description to the backend API using a Volley request
     */
    public void sendToAkhi() {

        String typed = editText.getText().toString();
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences1.edit();
        editor.putString("typed", typed);
        editor.apply();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        description = preferences.getString("typed", "");

        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userid = preferences2.getString("email", "");

        String url = "http://coms-309-017.class.las.iastate.edu:8080/Managers/" + description + "/" + userid;

        RequestQueue queue = Volley.newRequestQueue(Description.this);

        // Set Request Object:
        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String akhisResponse = response;
                SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("akhisResponse", akhisResponse);
                editor.apply();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(Description.this, "Not getting sent", Toast.LENGTH_LONG).show();
            }
        }) {
            // Remove getBody() and getBodyContentType() methods
        };

        queue.add(request);
    }

}

//package com.example.user_manager_v1;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//
//public class Description extends AppCompatActivity {
//
//    Button btn;
//
//    /*
//    Sending to Akhi
//     */
//    String userid = "";
//    String description = "";
//
//    /*
//    Storing from Akhi
//     */
//    EditText editText;
//
//    String localDesc = "";
//
//    public static String storeDesc = ""; //stores backend response
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_description);
//
//        editText = findViewById(R.id.descid);
//
//        btn = findViewById(R.id.description);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendToAkhi();
//                Intent intent = new Intent(Description.this, ListViewer.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//
//    public void sendToAkhi() {
//
//        String typed = editText.getText().toString();
//        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        SharedPreferences.Editor editor = preferences1.edit();
//        editor.putString("typed", typed);
//        editor.apply();
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        description = preferences.getString("typed", "");
//
//        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        userid = preferences2.getString("email", "");
//
//        String url = "http://coms-309-017.class.las.iastate.edu:8080/Managers/" + description + "/" + userid;
//
//        RequestQueue queue = Volley.newRequestQueue(Description.this);
//
//        // Set Request Object:
//        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                String akhisResponse = response;
//                SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = preferences1.edit();
//                editor.putString("akhisResponse", akhisResponse);
//                editor.apply();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                System.out.println(error.getMessage());
//                Toast.makeText(Description.this, "Not getting sent", Toast.LENGTH_LONG).show();
//            }
//        }) {
//            // Remove getBody() and getBodyContentType() methods
//        };
//
//        queue.add(request);
//    }
//}




//package com.example.user_manager_v1;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import org.json.JSONObject;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.converter.scalars.ScalarsConverterFactory;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import retrofit2.Retrofit;
//import retrofit2.http.Body;
//import retrofit2.http.PUT;
//
//public class Description extends AppCompatActivity {
//
//    Button btn;
//    /*
//    Sending to Akhi
//     */
//
//    String userid = "";
//    String description = "";
//
//    /*
//    Storing from Akhi
//     */
//   // String akhisResponse = "";
//    EditText editText;
//
//    String localDesc = "";
//
//    public static String storeDesc = ""; //stores backend response
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_description);
//        editText = findViewById(R.id.descid);
//
//
//        btn = findViewById(R.id.description);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendToAkhi();
//                Intent intent = new Intent(Description.this, ListViewer.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//    }
//
//    public void sendToAkhi(){
//
//
//        String typed = editText.getText().toString();
//        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        SharedPreferences.Editor editor = preferences1.edit();
//        editor.putString("typed",typed);
//        editor.apply();
//
////        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
////        userid = preferences.getString("email","");
//
//
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        description = preferences.getString("typed","");
//
//        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        userid = preferences2.getString("email","");
//
//        String url = "http://coms-309-017.class.las.iastate.edu:8080/Managers/"+description+"/"+userid;
//
//        RequestQueue queue = Volley.newRequestQueue(Description.this);
//
//        // Set Request Object:
//        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                String akhisResponse = response;
//                SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = preferences1.edit();
//                editor.putString("akhisResponse",akhisResponse);
//                editor.apply();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                System.out.println(error.getMessage());
//                Toast.makeText(Description.this, "Not getting sent", Toast.LENGTH_LONG).show();
//            }
//        }){
//            // Remove getBody() and getBodyContentType() methods
//        };
//
//        queue.add(request);
//    }
// }
