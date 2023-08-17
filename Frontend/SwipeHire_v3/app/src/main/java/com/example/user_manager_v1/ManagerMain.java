package com.example.user_manager_v1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The ManagerMain class is an activity that serves as the main screen for managers in the application.
 * This class contains buttons to navigate to other screens, including the logout screen, the company search screen,
 * and the profile screen.
 *
 * @author Aryan Rao
 */
public class ManagerMain extends AppCompatActivity {

    // Button variables
    Button button, buttonC, m;

    /**
     * This method is called when the activity is starting.
     * It initializes the layout and sets the click listeners for the buttons.
     *
     * @param savedInstanceState a Bundle object containing the activity's previously saved state
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);

        // Initialize buttons
        button = findViewById(R.id.logout1);
        m = findViewById(R.id.edit1);
        buttonC = findViewById(R.id.companies1);

        // Set click listener for profile button
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for logout button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RaoH.class);
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for company search button
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManagerSearch.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
