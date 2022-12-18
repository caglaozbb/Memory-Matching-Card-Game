package com.example.yazlabharrypotter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class singleGame extends AppCompatActivity {

    private boolean flipped = false;
    private ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5;
    private ImageButton[] imageButtons = {imageButton1, imageButton2, imageButton3, imageButton4};

    private DatabaseReference database;

    private int[] listimage = new int[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);
        Timer timer = new Timer();
        //MediaPlayer mediaPlayer1= MediaPlayer.create(this,R.raw.applause);
        //MediaPlayer mediaPlayer2= MediaPlayer.create(this,R.raw.soundfail);


        database = FirebaseDatabase.getInstance().getReference("1");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // System.out.println(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        database.child("1").child("image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imageButton5 = (ImageButton) findViewById(R.id.imageButton5);

                String image = snapshot.child("1").getValue().toString();
                String imageDataBytes = image.substring(image.indexOf(",")+1);
                InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes(), Base64.DEFAULT));
               Bitmap bitmap= BitmapFactory.decodeStream(stream);
                imageButton5.setImageBitmap(bitmap);
                //System.out.println(imageDataBytes);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        imageButtons[0] = this.imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButtons[1] = this.imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButtons[2] = this.imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imageButtons[3] = this.imageButton4 = (ImageButton) findViewById(R.id.imageButton4);


        int imgback = R.drawable.indir;

        final int[] clicked = {0};
        final boolean[] twoTurned = {false};
        final boolean noteq=false;
        final int[] lastClicked = {-1};
        boolean[] isback = new boolean[4];


          listimage[0] = R.drawable.back_to_hogwarts___logo_png_by_mintmovi3_dclpm88_fullview;
          listimage[1] = R.drawable.back_to_hogwarts___logo_png_by_mintmovi3_dclpm88_fullview;
          listimage[2] = R.drawable.back_to_hogwarts___logo_png_by_mintmovi3_dclpm88_fullview;
          listimage[3] = R.drawable.back_to_hogwarts___logo_png_by_mintmovi3_dclpm88_fullview;



        Random rand = new Random();

        for (int i = 0; i < listimage.length; i++) {
            int randomIndexToSwap = rand.nextInt(listimage.length);
            int temp = listimage[randomIndexToSwap];
            listimage[randomIndexToSwap] = listimage[i];
            listimage[i] = temp;
        }

        for (int i = 0; i < 4; i++) {
            imageButtons[i].setImageResource(imgback);
            isback[i] = true;
            int finalI = i;
            imageButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isback[finalI] == true && !twoTurned[0]) {
                        imageButtons[finalI].setImageResource(listimage[finalI]);
                        isback[finalI] = false;
                        if (clicked[0] == 0) {
                            lastClicked[0] = finalI;
                        }
                        clicked[0]++;
                       /* if (clicked[0] == 1){
                            image
                        }*/
                    } else if (isback[finalI] == false) {
                        imageButtons[finalI].setImageResource(imgback);
                        isback[finalI] = true;
                        clicked[0]--;
                    }

                    if (clicked[0] == 2) {
                        twoTurned[0] = true;
                        if (listimage[finalI] == listimage[lastClicked[0]]) {
                            //  mediaPlayer1.start();
                            imageButtons[finalI].setClickable(false);
                            imageButtons[lastClicked[0]].setClickable(false);
                            twoTurned[0] = false;
                            clicked[0] = 0;
                        } else if (listimage[finalI] != listimage[lastClicked[0]]) {
                            //  mediaPlayer2.start();

                                 /* try {
                                      imageButtons[finalI].setImageResource(listimage[finalI]);
                                      Thread.sleep(2000);
                                    //  imageButtons[finalI].setImageResource(imgback);
                                      imageButtons[lastClicked[0]].setImageResource(imgback);
                                  } catch (InterruptedException e) {
                                      e.printStackTrace();
                                  }*/
                                  /*timer.schedule(new TimerTask() {
                                      @Override
                                      public void run() {
                                          imageButtons[finalI].setImageResource(imgback);
                                          imageButtons[lastClicked[0]].setImageResource(imgback);
                                      }
                                  }, 2000);*/
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    imageButtons[finalI].setImageResource(imgback);
                                    imageButtons[lastClicked[0]].setImageResource(imgback);
                                }}, 2000);

                            isback[finalI] = true;
                            isback[lastClicked[0]] = true;
                            twoTurned[0] = false;
                            clicked[0] = 0;
                        }
                    }
                    else if (clicked[0] == 0) {
                        twoTurned[0] = false;
                    }
                }

                ;

            });

        }
        ;


    }
}
