package com.example.medicore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LandingActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        getSupportActionBar().hide();

        btnSignIn = findViewById(R.id.landing_btn_signIn);
        btnSignIn.setOnClickListener(view -> {
            Intent i = new Intent(LandingActivity.this, SignInActivity.class);
            startActivity(i);
        });

        btnSignUp = findViewById(R.id.landing_btn_SignUp);
        btnSignUp.setOnClickListener(view -> {
            Intent i = new Intent(LandingActivity.this, SignUpActivity.class);
            startActivity(i);
        });

    }
}