package com.example.neo.nougatstudy;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by neo on 2017-07-17.
 */

public class BitmapButton extends AppCompatButton {
    public BitmapButton(Context context) {
        super(context);
        init(context);
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        setBackgroundResource(R.drawable.gift_button);

        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);
        setTextColor(Color.BLACK);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.home_button);
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.drawable.gift_button);
                break;
        }

        invalidate();   //버튼의 그래픽을 다시 그려 줘라.

        return true;
    }
}
