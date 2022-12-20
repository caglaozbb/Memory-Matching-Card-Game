package com.example.yazlabharrypotter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class person extends AppCompatActivity {


    private Button btn_2,btn_3;
    private FirebaseUser currentUser;
    private FirebaseAuth auth;

    public void init (){
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        init();

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout2();
            }
        });


        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout4();
            }
        });
    }

    private void layout4() {

        Intent multiGameIntent = new Intent(person.this, single4.class);
        startActivity(multiGameIntent);
    }

    private void layout2() {

        Intent singleGameIntent = new Intent(person.this, singleGame.class);
        startActivity(singleGameIntent);
    }

    @Override
    protected void onStart() {

        if (currentUser == null) {
            Intent mainIntent = new Intent(person.this, single4.class);
            startActivity(mainIntent);
            finish();
        }
        super.onStart();

    }
}