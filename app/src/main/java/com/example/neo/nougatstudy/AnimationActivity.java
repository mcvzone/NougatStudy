package com.example.neo.nougatstudy;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class AnimationActivity extends AppCompatActivity {

    TextView text;
    ProgressBar bar, bar2, bar3;
    Animation animation, animation2;

    ArrayList<Drawable> imageList = new ArrayList<Drawable>();

    ImageView imageView;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        text = (TextView)findViewById(R.id.ANIMATION_TV_TEXT);

        animation = AnimationUtils.loadAnimation(this, R.anim.flow);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.grow);

        findViewById(R.id.ANIMATION_BT_ANISTART).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                text.startAnimation(animation);
            }
        });

        Resources resources = getResources();
        imageList.add(resources.getDrawable(R.drawable.cry));
        imageList.add(resources.getDrawable(R.drawable.shy));
        imageList.add(resources.getDrawable(R.drawable.laugh));
        imageList.add(resources.getDrawable(R.drawable.nothing));
        imageList.add(resources.getDrawable(R.drawable.nothing2));

        imageView = (ImageView)findViewById(R.id.ANIMATION_IV_IMOTICON);

        findViewById(R.id.ANIMATION_BT_IMOTICON).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AnimThread animThread = new AnimThread();
                animThread.start();
            }
        });

        bar = (ProgressBar) findViewById(R.id.animationProgressBar);
        bar2 = (ProgressBar)findViewById(R.id.animationProgressBar2);
        bar3 = (ProgressBar)findViewById(R.id.animationProgressBar3);

        bar.setAnimation(animation2);
        bar2.setAnimation(animation2);
        bar3.setAnimation(animation2);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            animation2.start();
        } else {
            animation2.reset();
        }
    }

    class AnimThread extends Thread{
        public void run(){
            int index = 0;
            for( int i=0; i<100; i++){
                index = i % 5;
                final Drawable drawable = imageList.get(index);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (Exception e){}
            }
        }
    }
}
