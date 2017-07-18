package com.example.neo.nougatstudy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by neo on 2017-07-17.
 */

public class SingerItemView extends LinearLayout{
    TextView textview;
    TextView textview2;
    ImageView imageView;

    public SingerItemView(Context context) {
        super(context);
        init(context);
    }

    public SingerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context){
        LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        textview = (TextView)findViewById(R.id.SINGER_TV_TEXTVIEW);
        textview2 = (TextView)findViewById(R.id.SINGER_TV_TEXTVIEW2);
        imageView = (ImageView)findViewById(R.id.SINGER_IV_IMAGEVIEW);
    }

    public void setName(String name){
        textview.setText(name);
    }
    public void setMobile(String mobile){
        textview2.setText(mobile);
    }
    public void setImageView(int img){
        imageView.setImageResource(img);
    }
}
