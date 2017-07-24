package com.example.neo.nougatstudy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by neo on 2017-07-24.
 */

public class MonthItemView extends RelativeLayout {

    TextView textView;

    public MonthItemView(Context context) {
        super(context);
        init(context);
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.month_item, this, true);

        textView = findViewById(R.id.SCHEDULE_TV_TEXT);

    }

    public void setDay(int day){
        textView.setText(""+day);
    }
}
