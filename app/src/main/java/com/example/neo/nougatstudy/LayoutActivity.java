package com.example.neo.nougatstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class LayoutActivity extends AppCompatActivity {
    ImageView iv, iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        iv = (ImageView) findViewById(R.id.imageView);
        iv2 = (ImageView) findViewById(R.id.imageView2);

        findViewById(R.id.LAYOUT_BT_IMGCONVERT).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if( iv.getVisibility() == View.VISIBLE){
                    iv.setVisibility(View.INVISIBLE);
                    iv2.setVisibility(View.VISIBLE);
                } else {
                    iv.setVisibility(View.VISIBLE);
                    iv2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
