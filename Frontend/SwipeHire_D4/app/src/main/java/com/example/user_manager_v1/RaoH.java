package com.example.user_manager_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RaoH extends AppCompatActivity {

    Button sign_in, sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rao);
    }


    public void goToSignUp(View view){
        Intent intent = new Intent(RaoH.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }


    public void goToSignIn(View view){
        Intent intent = new Intent(RaoH.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

}