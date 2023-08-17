package com.example.user_manager_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Displays the details of a company and allows the user to add a job for the company.
 * @author - Hrishikesha Kyathsandra
 */
public class CompanyData extends AppCompatActivity {

    Button addJob;

    /**
     * Sets up the layout of the activity and initializes the add job button.
     *
     * @param savedInstanceState a saved instance of the activity (not used)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_data);

        addJob = findViewById(R.id.button23);
        addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyData.this, JobFields.class);
                startActivity(intent);
                finish();
            }
        });
    }
}


//package com.example.user_manager_v1;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.user_manager_v1.Description;
//import androidx.appcompat.app.AppCompatActivity;
//
//
//
//public class CompanyData extends AppCompatActivity {
//
////    String s = Description.storeDesc;
//    Button addJob;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_company_data);
////        TextView tv = findViewById(R.id.company_data);
////        tv.setText(Description.storeDesc);
//        addJob = findViewById(R.id.button23);
//        addJob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CompanyData.this, JobFields.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//}