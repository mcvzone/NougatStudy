package com.example.neo.nougatstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PickerActivity extends AppCompatActivity {

    TextView textView;
    Picker picker;
    SimpleDateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        textView = (TextView)findViewById(R.id.TEXT);
        picker = (Picker)findViewById(R.id.Picker);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        picker.setOnDateTimeChangeListener(new OnDateTimeChangeListener() {
            @Override
            public void onDateTimeChange(Picker view, int year, int monthofyear, int dayofmonth, int hour, int minute) {
                Log.d("MYLOG", "date : " + year + " _ " + monthofyear + " _ " + dayofmonth + " _ " + hour + " _ " + minute);

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthofyear, dayofmonth, hour, minute);
                String curTime = format.format(calendar.getTime());
                Log.d("MYLOG", "curTime : " + curTime);
                textView.setText(curTime);
            }
        });
    }
}
