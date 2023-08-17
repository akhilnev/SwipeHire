package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button add, sub, div, mult;
    EditText editn1, editn2;
    TextView textView;
    int num1, num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        div = findViewById(R.id.div);
        mult = findViewById(R.id.multi);

        editn1 = findViewById(R.id.number1);
        editn2 = findViewById(R.id.number2);

        textView = findViewById(R.id.ans);

        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        div.setOnClickListener(this);
        mult.setOnClickListener(this);
    }

    public int parser(EditText text){
        if(text.getText().toString().equals("")){
            Toast.makeText(this, "Enter number", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else{
            return Integer.parseInt(text.getText().toString());
        }
    }


    @Override
    public void onClick(View view) {

        num1 = parser(editn1);
        num2 = parser(editn1);

        switch (view.getId()){
            case R.id.add:
                textView.setText("Answer = " + (num1+num2));
                break;
            case R.id.sub:
                textView.setText("Answer = " + (num1-num2));
                break;
            case R.id.div:
                textView.setText("Answer = " + (float)num1/num2);
                break;
            case R.id.multi:
                textView.setText("Answer = " + (num1*num2));
                break;
            default:
                textView.setText("Invalid");
                break;
        }
    }
}