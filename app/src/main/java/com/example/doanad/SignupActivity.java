package com.example.doanad;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    Button buttonBack;
    EditText extUsername, extEmail, extPass, extEPass;
    Button Btn_signup;
    String EdUsername, EdEmail, EdPass, EdEPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        buttonBack = findViewById(R.id.btn_Signup_home);
        Btn_signup = findViewById(R.id.btn_signup);
        extUsername = findViewById(R.id.ExtUsername);
        extEmail = findViewById(R.id.ExtEmail);
        extPass = findViewById(R.id.ExtPass);
        extEPass = findViewById(R.id.ExtEPass);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EdUsername = String.valueOf(extUsername.getText());
                EdEmail = String.valueOf(extEmail.getText());
                EdPass = String.valueOf(extPass.getText());
                EdEPass = String.valueOf(extEPass.getText());
                if (TextUtils.isEmpty(EdEmail)){
                    Toast.makeText(SignupActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(EdPass)) {
                    Toast.makeText(SignupActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(EdEPass)){
                    Toast.makeText(SignupActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(EdEmail, EdPass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication created.",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(SignupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });







    }
}