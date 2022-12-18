package com.example.yazlabharrypotter;

import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;

public class card {
    private String isim;
    private String ev;
    private int puan;
    private String image;

    public void init (){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        StorageReference storageRef = storage.getReference();
        StorageReference imagesRef = storageRef.child("images");

    }


    public card(String Isim,String Ev,int Puan,String Image){
        this.isim=Isim;
        this.ev=Ev;
        this.puan=Puan;
        this.image=Image;
    }
    public card(){

    }


}
