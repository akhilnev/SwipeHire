package com.example.datatransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Transfer extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name: ");
        String email = intent.getStringExtra("Email: ");
        String ph = intent.getStringExtra("Phone Number: ");
        String ut = intent.getStringExtra("User Type: ");

        tv = findViewById(R.id.transferred);
        tv.setText("Name: "+name+"\nEmail: "+email+"\nPhone Number: "+ph+"\nUser Type: "+ut);

    }
}