package com.example.neo.nougatstudy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class SubScreenActivity extends AppCompatActivity {

    FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_screen);

        fl = (FrameLayout)findViewById(R.id.SUBSCREEN_FL_SUBSCREEN);

        findViewById(R.id.SUBSCREEN_BT_GETSUBSCREEN).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.activity_sub_screen_sub, fl);

                fl.findViewById(R.id.SUBSCREEN_SUB_BT_CLOSE).setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), Activity.RESULT_OK + "코드로 종료 합니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("name", "성공했다");
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                });
            }
        });
    }
}
