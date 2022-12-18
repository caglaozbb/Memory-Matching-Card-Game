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

public class signup extends AppCompatActivity {

    private Button btn_signup;
    private EditText txtUsername, txtEmail, txtPassword;
    private FirebaseAuth auth;


    public void init(){

        btn_signup = (Button) findViewById(R.id.btn_signup);
        txtUsername = (EditText) findViewById(R.id.user_name);
        txtEmail = (EditText) findViewById(R.id.email_address);
        txtPassword = (EditText) findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNewAccount();
            }
        });
    }

    private void createNewAccount() {

        String userName = txtUsername.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"The email field cannot be empty!",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,"The password field cannot be empty",Toast.LENGTH_LONG).show();
        }
        else {
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(signup.this,"The account has been successfully created.",Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(signup.this,login.class);
                        startActivity(loginIntent);
                        finish();
                    }

                    else{
                        Toast.makeText(signup.this,"The account could not be created.",Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

}