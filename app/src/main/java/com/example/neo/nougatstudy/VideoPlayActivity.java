package com.example.neo.nougatstudy;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlayActivity extends AppCompatActivity {

    VideoView videoPanel;
    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        videoPanel = (VideoView)findViewById(R.id.videoPanel);

        MediaController controller = new MediaController(this);
        videoPanel.setMediaController(controller);
        videoPanel.setVideoURI(Uri.parse(url));
        videoPanel.requestFocus();//파일의 정보를 가져오고 준비과정을 마친다.

        videoPanel.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Toast.makeText(getApplicationContext(), "준비완료", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.videoPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPanel.seekTo(0);
                videoPanel.start();
            }
        });
    }
}
