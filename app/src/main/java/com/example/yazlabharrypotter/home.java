package com.example.yazlabharrypotter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity {

    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onStart() {

        if (currentUser == null) {

            Intent mainIntent = new Intent(home.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
        super.onStart();

    }
}
