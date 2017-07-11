package com.example.neo.nougatstudy;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class ProgressActivity extends AppCompatActivity {
    ProgressBar pb;
    EditText et;
    SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        pb = (ProgressBar)findViewById(R.id.PROGRESS_PB_BAR);
        et = (EditText)findViewById(R.id.PROGRESS_ET_VALUE);
        findViewById(R.id.PROGRESS_BT_VALUE).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String inputStr = et.getText().toString().trim();
                int input = Integer.parseInt(inputStr);
                pb.setProgress(input);
                pb.setMax(100);
            }
        });

        findViewById(R.id.PROGRESS_BT_CIRCLE).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                showProgressDialog();
            }
        });

        sb = (SeekBar)findViewById(R.id.PROGRESS_SB_SEEKBAR);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                et.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void showProgressDialog(){
        ProgressDialog pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("데이터 확인중");
        pd.show();
    }
}
