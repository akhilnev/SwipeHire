package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Swiper extends AppCompatActivity {

    private ViewPager2 viewPager;
    private StringAdapter stringAdapter;
    private ArrayList<String> akhiList;
    private WebSocketClient mWebSocketClient;
    private String myuserid = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiper);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        myuserid = preferences.getString("email","");

        akhiList = new ArrayList<>();

        // Initialize the adapter
        stringAdapter = new StringAdapter(this, akhiList);

        // Set the adapter to the ViewPager2
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(stringAdapter);

        // Create WebSocket connection
        //createWebSocketConnection();

        // Register a callback to handle swipe direction and display the next element in the list
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            private int lastPosition = -1;

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // Check if the last position is initialized
                if (lastPosition != -1) {
                    // If swiped in any direction, move to the next element in the list
                    if (position != lastPosition) {
                        int direction = position - lastPosition;
                        if (direction > 0 || direction < -1) {
                            // Swiped right
                            Toast.makeText(Swiper.this, "User accepted", Toast.LENGTH_SHORT).show();
                            //mWebSocketClient.send("User accepted");
                        } else {
                            // Swiped left
                            Toast.makeText(Swiper.this, "User rejected", Toast.LENGTH_SHORT).show();
                            //mWebSocketClient.send("User rejected");
                        }

                        int nextPosition = (lastPosition + 1) % akhiList.size();
                        viewPager.setCurrentItem(nextPosition, false);
                    }
                }

                lastPosition = viewPager.getCurrentItem();
            }
        });

        // Make a GET request to retrieve the list of companies
        String url = "http://coms-309-017.class.las.iastate.edu:8080/candidate/getAllResumes";
        RequestQueue queue = Volley.newRequestQueue(Swiper.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                response -> {

                    String[] resumes = response.split("\\$");
                    for (String name : resumes) {
                        name = name.replace("[", "");
                        name = name.replace("]", "");
                        name = name.replace("\"", "");
                        name = name.replace("_", " ");
                        akhiList.add(name);

                    }
                    stringAdapter.updateData(akhiList);
                    viewPager.setAdapter(stringAdapter);
                },
                error -> {
                    // Handle the error
                    Toast.makeText(this, "Error retrieving resumes: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );
        queue.add(stringRequest);

    }

//    private void createWebSocketConnection() {
//        URI uri;
//        try {
//            uri = new URI("ws://coms-309-017.class.las.iastate.edu:8080/websocketswipe/" + userid);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        mWebSocketClient = new WebSocketClient(uri) {
//            @Override
//            public void onOpen(ServerHandshake handshakedata) {
//                runOnUiThread(() -> {
//                    Toast.makeText(Swiper.this, "WebSocket connected", Toast.LENGTH_SHORT).show();
//                });
//            }
//
//            @Override
//            public void onMessage(String message) {
//                // You can implement any action needed upon receiving a message from the server
//            }
//
//            @Override
//            public void onClose(int code, String reason, boolean remote) {
//                runOnUiThread(() -> {
//                    Toast.makeText(Swiper.this, "WebSocket disconnected", Toast.LENGTH_SHORT).show();
//                });
//            }
//
//            @Override
//            public void onError(Exception ex) {
//                runOnUiThread(() -> {
//                    Toast.makeText(Swiper.this, "WebSocket error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//            }
//        };
//        mWebSocketClient.connect();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mWebSocketClient != null) {
//            mWebSocketClient.close();
//        }
//    }
}