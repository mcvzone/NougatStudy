package com.example.neo.nougatstudy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class ColorDialogActivity extends AppCompatActivity {
    GridView gridView;
    ColorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_dialog);

        gridView = (GridView)findViewById(R.id.COLOR_DIALOG_POPUP);

        adapter = new ColorAdapter();
        adapter.addItem(0xff000000);
        adapter.addItem(0xffff0000);
        adapter.addItem(0xffffff00);
        adapter.addItem(0xff00ff00);
        adapter.addItem(0xff00ffff);
        adapter.addItem(0xff0000ff);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int color = (Integer)adapter.getItem(i);

                Intent intent = new Intent();
                intent.putExtra("color", color);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }


    class ColorAdapter extends BaseAdapter{
        ArrayList<Integer> items = new ArrayList<Integer>();

        public void addItem(int color){
            items.add(color);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ColorItemView colorItemView = null;

            if (view == null) {
                colorItemView = new ColorItemView(getApplicationContext());
            } else {
                colorItemView = (ColorItemView)view;
            }

            int color = (Integer)items.get(i);
            colorItemView.setColor(color);

            return colorItemView;
        }
    }

}
