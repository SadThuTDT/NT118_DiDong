package com.example.doanad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {
    Button buttonBack;

    EditText EdtEmail, EdtPass;

    Button BtnSignin;

    String edEmail, edPass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        buttonBack =  findViewById(R.id.btn_Signin_home);
        EdtEmail = findViewById(R.id.ed_email);
        EdtPass = findViewById(R.id.ed_pass);
        BtnSignin = findViewById(R.id.btn_Signin);
        mAuth = FirebaseAuth.getInstance();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        BtnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edEmail = String.valueOf(EdtEmail.getText());
                edPass = String.valueOf(EdtPass.getText());
                if (TextUtils.isEmpty(edEmail)){
                    Toast.makeText(SigninActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edPass)) {
                    Toast.makeText(SigninActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(edEmail, edPass)
                        .addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SigninActivity.this, "Successed",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(SigninActivity.this, MainDashboardActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(SigninActivity.this, "Sign in failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}