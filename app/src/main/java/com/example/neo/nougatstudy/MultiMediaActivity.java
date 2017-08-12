package com.example.neo.nougatstudy;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MultiMediaActivity extends AppCompatActivity {

    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";
    public String filename;
    MediaRecorder recorder;
    MediaPlayer player;
    int position = 0;//위치

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_media);

        File sdcard = Environment.getExternalStorageDirectory();    //외부저장소 영역을 접근 할수 있는 객체.
        Log.d("MYLOG", "sdcard path : " + sdcard.getPath());
        File file = new File(sdcard, "test/recorded.mpr");
        filename = file.getAbsolutePath();
        Log.d("MYLOG", "저장할 파일명 : "+ filename);

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
        findViewById(R.id.multiMediaBtn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recodeAudio();
            }
        });

        findViewById(R.id.multiMediaBtn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecodeAudio();
            }
        });
    }

    public void playAudio(){
        try {
            closePlayer();
            player = new MediaPlayer();
            //player.setDataSource(url);
            player.setDataSource(filename);
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

    public void recodeAudio(){
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//어디서 들어 오는 소리를 녹음 할 것이냐를 셋팅.
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder( MediaRecorder.AudioEncoder.DEFAULT);//바이트를 처리 해주는 기본 클래스
        recorder.setOutputFile(filename);

        try {
            recorder.prepare();
            recorder.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void stopRecodeAudio(){
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
            Toast.makeText(getApplicationContext(), "정지됨", Toast.LENGTH_SHORT).show();
        }
    }
}
