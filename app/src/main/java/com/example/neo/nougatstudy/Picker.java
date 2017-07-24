package com.example.neo.nougatstudy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by neo on 2017-07-19.
 */

public class Picker extends LinearLayout {
    DatePicker datePicker;
    TimePicker timePicker;
    CheckBox checkBox;
    OnDateTimeChangeListener listener;

    public Picker(Context context) {
        super(context);
        init(context);
    }

    public Picker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picker, this, true);

        datePicker = findViewById(R.id.PICKER_DP_PICKER);
        timePicker = findViewById(R.id.PICKER_TP_PICKER);
        checkBox = findViewById(R.id.PICKER_CB_TIMECHECK);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);

        timePicker.setEnabled(true);
        checkBox.setChecked(true);
        timePicker.setVisibility(checkBox.isChecked()? View.VISIBLE:View.GONE);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener(){

            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                if( listener != null ){
                    listener.onDateTimeChange(Picker.this, i, i1, i2, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                }
            }
        });

        timePicker.setCurrentHour(h);
        timePicker.setCurrentMinute(m);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                listener.onDateTimeChange(Picker.this, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), i, i1);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                timePicker.setEnabled(b);
                timePicker.setVisibility(checkBox.isChecked()? View.VISIBLE:View.GONE);
            }
        });
    }

    public void setOnDateTimeChangeListener(OnDateTimeChangeListener listener){
        this.listener = listener;
    }

    public void updateDateTime(int y, int mon, int d, int h, int m){
        datePicker.updateDate(y, mon, d);
        timePicker.setCurrentMinute(m);
        timePicker.setCurrentHour(h);
    }
}
