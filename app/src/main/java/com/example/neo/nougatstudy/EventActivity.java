package com.example.neo.nougatstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        textView = (TextView)findViewById(R.id.EVENT_TXT_VIEW);
    }

    private void println(String sTxt){
        textView.append(sTxt+"\n");
    }
}
