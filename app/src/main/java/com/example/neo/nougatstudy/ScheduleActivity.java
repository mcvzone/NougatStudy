package com.example.neo.nougatstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class ScheduleActivity extends AppCompatActivity {

    TextView monthtext;
    Button monthPre;
    Button monthNext;
    GridView monthView;
    MonthAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        monthtext = (TextView)findViewById(R.id.SCHEDULE_TX_YYYYMMDD);
        monthPre = (Button)findViewById(R.id.SCHEDULE_BT_BACK);
        monthNext = (Button)findViewById(R.id.SCHEDULE_BT_FOR);
        monthView = (GridView)findViewById(R.id.SCHEDULE_GV_MAIN);

        adapter = new MonthAdapter();
        monthView.setAdapter(adapter);

        monthtext.setText(adapter.getCurYear() + " 년" + adapter.getCurMon() + " 월");

        monthPre.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                adapter.setPrevMonth();
                adapter.notifyDataSetChanged();
                monthtext.setText(adapter.getCurYear() + " 년" + adapter.getCurMon() + " 월");
            }
        });

        monthNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                adapter.setNextMonth();
                adapter.notifyDataSetChanged();
                monthtext.setText(adapter.getCurYear() + " 년" + adapter.getCurMon() + " 월");
            }
        });
    }



    class MonthAdapter extends BaseAdapter{

        MonthItem[] items;
        Calendar calendar;
        int firstDay;
        int lastDay;
        int curYear;
        int curMon;


        public MonthAdapter() {
            this.items = new MonthItem[7*6];
            calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            reCalculate();
            reSetDayNumbers();
        }

        public void setPrevMonth(){
            calendar.add(Calendar.MONTH, -1);
            reCalculate();
            reSetDayNumbers();
        }

        public void setNextMonth(){
            calendar.add(Calendar.MONTH, 1);

            reCalculate();
            reSetDayNumbers();
        }

        public int getCurYear(){
            return curYear;
        }

        public int getCurMon(){
            return curMon;
        }

        public void reCalculate(){
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            firstDay = getFirstDay(dayOfWeek);

            curYear = calendar.get(Calendar.YEAR);
            curMon = calendar.get(Calendar.MONTH)+1;
            lastDay = getLastDay();
        }

        public void reSetDayNumbers(){
            for( int i=0; i<42; i++ ){
                int dayNumber = (i+1) - firstDay;
                if( dayNumber < 1 || dayNumber > lastDay ){
                    dayNumber = 0;
                }

                items[i] = new MonthItem(dayNumber);
            }
        }

        public int getFirstDay(int dayOfWeek){
            int result = 0;
            if (dayOfWeek == Calendar.SUNDAY) {
                result = 0;
            } else if (dayOfWeek == Calendar.MONDAY) {
                result = 1;
            } else if (dayOfWeek == Calendar.TUESDAY) {
                result = 2;
            } else if (dayOfWeek == Calendar.WEDNESDAY) {
                result = 3;
            } else if (dayOfWeek == Calendar.THURSDAY) {
                result = 4;
            } else if (dayOfWeek == Calendar.FRIDAY) {
                result = 5;
            } else if (dayOfWeek == Calendar.SATURDAY) {
                result = 6;
            }
            return result;
        }

        public int getLastDay(){
            switch(curMon){
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    return 31;
                case 4:case 6: case 9: case 11:
                    return 30;
                case 2:
                    if((curYear%4==0)&&(curYear%100!=0)||(curYear%400==0)){
                        return 29;
                    } else {
                        return 28;
                    }
                default:
                    return 0;
            }
        }

        @Override
        public int getCount() {
            return 42;
        }

        @Override
        public Object getItem(int i) {
            return items[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MonthItemView monthview = null;

            if (view == null) {
                monthview = new MonthItemView(getApplicationContext());
            } else {
                monthview = (MonthItemView) view;
            }

            monthview.setDay(items[i].day);

            return monthview;
        }
    }
}
