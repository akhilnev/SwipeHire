package com.example.user_manager_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TestList extends AppCompatActivity {

    public static String testType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);

        ArrayList<String> testList = new ArrayList<>();
        testList.add("Java");
        testList.add("Python");
        testList.add("C++");
        testList.add("Android");

        ListView listView = findViewById(R.id.testlist);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.row,testList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //put it in a for loop using the list akhilesh sends u.

                if(position==0){
                    startActivity(new Intent(TestList.this, JavaQuiz.class));
                    testType = "Java";
                }
                else if(position == 1){
                    startActivity(new Intent(TestList.this, JavaQuiz.class));
                    testType = "Python";
                }

                else if(position ==2){
                    startActivity(new Intent(TestList.this, JavaQuiz.class));
                    testType = "C++";
                }

                else if(position ==3){
                    startActivity(new Intent(TestList.this, JavaQuiz.class));
                    testType = "Android";
                }
            }

        });
    }

}
