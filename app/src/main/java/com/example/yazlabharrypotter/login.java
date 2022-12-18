package com.example.yazlabharrypotter;

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
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    private Button btn_login;
    private EditText txtEmail,txtPassword;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init(){

        btn_login = (Button) findViewById(R.id.btn_login);
        txtEmail= (EditText) findViewById(R.id.email_address);
        txtPassword= (EditText) findViewById(R.id.password);
        auth= FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                loginUser();
            }
        });
    }

    private void loginUser() {

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email field is required",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"This field is required",Toast.LENGTH_LONG).show();
        }
        else{
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                   if (task.isSuccessful()){
                       Toast.makeText(login.this,"You have successfully logged in",Toast.LENGTH_LONG).show();
                       Intent homeIntent = new Intent(login.this, home.class);
                       startActivity(homeIntent);
                   }
                   else {
                       Toast.makeText(login.this,"Login failed",Toast.LENGTH_LONG).show();
                   }

                }
            });
        }

    }
}