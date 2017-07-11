package com.example.neo.nougatstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class AnimationTabActivity extends AppCompatActivity {

    LinearLayout ll;
    Animation trans_left, trans_right;
    Button btn;
    boolean isOpen = false;

    SlidingAnimationListener listener = new SlidingAnimationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_tab);

        ll = (LinearLayout)findViewById(R.id.ANITAB_LL_TAB);
        trans_left = AnimationUtils.loadAnimation(this, R.anim.trans_left);
        trans_right = AnimationUtils.loadAnimation(this, R.anim.trans_right);

        trans_left.setAnimationListener(listener);
        trans_right.setAnimationListener(listener);

        btn = (Button)findViewById(R.id.ANITAB_BT_OPEN);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if( isOpen ) {
                    ll.startAnimation(trans_right);
                } else {
                    ll.setVisibility(View.VISIBLE);
                    ll.startAnimation(trans_left);
                }
            }
        });
    }

    class SlidingAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if( isOpen ) {
                ll.setVisibility(View.GONE);
                btn.setText("열기");
                isOpen = false;
            } else {
                btn.setText("닫기");
                isOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
