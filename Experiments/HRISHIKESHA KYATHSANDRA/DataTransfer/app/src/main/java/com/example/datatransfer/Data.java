package com.example.datatransfer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Data extends AppCompatActivity implements View.OnClickListener {

    EditText name, number, email, user;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        name = findViewById(R.id.name);
        number = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email);
        user = findViewById(R.id.userType);

        b = findViewById(R.id.profileButton);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //First get data from edit text.
        String n = name.getText().toString();
        String num = number.getText().toString();
        String em = email.getText().toString();
        String us = user.getText().toString();

        Intent intent = new Intent(this, Transfer.class);
        intent.putExtra("Name: ", n);
        intent.putExtra("Email: ", em);
        intent.putExtra("Phone Number: ", num);
        intent.putExtra("User Type: ", us);
        startActivity(intent);
    }
}