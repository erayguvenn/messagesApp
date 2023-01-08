package com.example.messagesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            return;
        } else {
            startActivity(new Intent(SplashActivity.this, SignInActivity.class));
        }


        findViewById(R.id.girisyap).setOnClickListener(view ->
                startActivity(new Intent(SplashActivity.this, SignInActivity.class)));

        findViewById(R.id.kayitol).setOnClickListener(view ->
                startActivity(new Intent(SplashActivity.this, SignUpActivity.class)));

    }

}