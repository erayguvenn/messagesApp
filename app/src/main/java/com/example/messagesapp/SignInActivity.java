package com.example.messagesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText emailText;
    EditText passwordText;
    Button submitButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        emailText=(EditText) findViewById(R.id.signinEmail);
        passwordText=(EditText) findViewById(R.id.signinPassword);
        submitButton=(Button) findViewById(R.id.signinSubmit);

        findViewById(R.id.uyeolButton).setOnClickListener(view ->
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class)));

        submitButton.setOnClickListener(view -> {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();

            if(email.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Email alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
                return;
            }

            if(password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Şifre alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(SignInActivity.this, task -> {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Giriş başarılı.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Giriş başarısız.", Toast.LENGTH_SHORT).show();
                }

            });
        });


    }
}