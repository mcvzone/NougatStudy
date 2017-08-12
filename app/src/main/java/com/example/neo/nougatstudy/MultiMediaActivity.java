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
                pauseAudio();
            }
        });
        findViewById(R.id.multiMediaBtn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resumeAudio();
            }
        });
        findViewById(R.id.multiMediaBtn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });
    }

    public void playAudio(){
        try {
            closePlayer();
            player = new MediaPlayer();
            player.setDataSource(url);
            player.prepare();
            player.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pauseAudio(){
        if (player != null) {
            position = player.getCurrentPosition();
            player.pause();
        }
    }

    public void resumeAudio(){
        if (player != null && !player.isPlaying()) {
            player.seekTo(position);
            player.start();
        }
    }

    public void stopAudio(){
        if (player != null && player.isPlaying()) {
            player.stop();
        }
    }

    public void closePlayer(){
        if( player != null ){
            player.release();
            player = null;
        }
    }
}
