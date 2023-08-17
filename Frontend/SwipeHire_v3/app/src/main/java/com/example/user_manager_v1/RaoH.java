package com.example.user_manager_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class represents the main activity of the RaoH application.
 * It provides functionality to navigate to the Sign Up and Sign In screens.
 *
 * @author Aryan Rao
 */
public class RaoH extends AppCompatActivity {

    Button sign_in, sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rao);
    }

    /**
     * This method navigates to the Sign Up screen.
     *
     * @param view The view that triggered the method call.
     */
    public void goToSignUp(View view){
        Intent intent = new Intent(RaoH.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * This method navigates to the Sign In screen.
     *
     * @param view The view that triggered the method call.
     */
    public void goToSignIn(View view){
        Intent intent = new Intent(RaoH.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

}
