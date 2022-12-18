package com.example.yazlabharrypotter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity {

    private Button btn_singlePlayer,btn_multiPlayer;
    private FirebaseUser currentUser;
    private FirebaseAuth auth;

    public void init (){
        btn_singlePlayer = (Button) findViewById(R.id.btn_singlePlayer);
        btn_multiPlayer = (Button) findViewById(R.id.btn_multiPlayer);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        
        btn_singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singlePlayer();
            }
        });


        btn_multiPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                multiPlayer();
            }
        });
    }

    private void multiPlayer() {

        Intent multiGameIntent = new Intent(home.this, multiGame.class);
        startActivity(multiGameIntent);
    }

    private void singlePlayer() {

        Intent singleGameIntent = new Intent(home.this, singleGame.class);
        startActivity(singleGameIntent);
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
