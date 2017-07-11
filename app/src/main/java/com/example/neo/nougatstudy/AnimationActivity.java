package com.example.neo.nougatstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {

    TextView text;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        text = (TextView)findViewById(R.id.ANIMATION_TV_TEXT);

        animation = AnimationUtils.loadAnimation(this, R.anim.flow);

        findViewById(R.id.ANIMATION_BT_ANISTART).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                text.startAnimation(animation);
            }
        });
    }
}
