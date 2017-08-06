package com.example.neo.nougatstudy;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MultiMediaActivity extends AppCompatActivity {

    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";
    MediaPlayer player;
    int position = 0;//위치

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_media);

        findViewById(R.id.multiMediaBtn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio();
            }
        });
        findViewById(R.id.multiMediaBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findViewById(R.id.multiMediaBtn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findViewById(R.id.multiMediaBtn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void playAudio(){
        try {
            player = new MediaPlayer();
            player.setDataSource(url);
            player.prepare();
            player.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void killPlayer(){
    }
}
