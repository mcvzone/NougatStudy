package com.example.neo.nougatstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class ComponentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);

        Intent intent = getIntent();
        String sData = intent.getStringExtra("data");

        processIntent(intent);
    }

    private void processIntent(Intent intent){
        if( intent != null ){
            ArrayList<String> names = (ArrayList<String>)intent.getSerializableExtra("names");
            if( names != null ){
                Toast.makeText(getApplicationContext(), "전달받은 이름 리스트 갯수 : " + names.size(), Toast.LENGTH_SHORT).show();
            }

            SimpleData data = (SimpleData)intent.getParcelableExtra("data");
            if (data != null) {
                Toast.makeText(getApplicationContext(), "전달받은 SimpleData : " + data.message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
