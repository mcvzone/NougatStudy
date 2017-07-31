package com.example.neo.nougatstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaintBoardActivity extends AppCompatActivity {
    PaintBoardView board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_board);

        board = (PaintBoardView)findViewById(R.id.PAINTBOARD_VIEW);

        Button button = (Button)findViewById(R.id.PAINT_BT_PALET);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ColorDialogActivity.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            if (data != null) {
                int color = data.getIntExtra("color", 0);
                board.setColor(color);
            }
        }
    }
}
