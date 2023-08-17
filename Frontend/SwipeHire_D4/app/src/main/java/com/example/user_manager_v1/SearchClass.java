package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class SearchClass extends AppCompatActivity {

    private Button button, buttonC, buttonS, msg, edit, recj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_class);

        button = findViewById(R.id.logout);
        buttonC = findViewById(R.id.companies);
        buttonS = findViewById(R.id.searchC);
        msg = findViewById(R.id.fab);
        edit = findViewById(R.id.edit);
        recj = findViewById(R.id.recjobs);



        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(SearchClass.this, Chat.class);
                startActivity(intent);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchClass.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RaoH.class);
                startActivity(intent);
                finish();
            }
        });

        recj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecommendJobs.class);
                startActivity(intent);
                finish();
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Companies.class);
                startActivity(intent);
                finish();
            }
        });

        buttonS.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchCompany.class);
                startActivity(intent);
                finish();
            }
        }));
    }
}
