package com.example.neo.nougatstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {
    SingerAdapter adapter;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = (GridView)findViewById(R.id.GRID_GV_GRID);

        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("소시", "01020102010", R.drawable.music1));
        adapter.addItem(new SingerItem("트렙", "01001991020", R.drawable.music1));
        adapter.addItem(new SingerItem("송창식", "01011199922", R.drawable.music2));
        adapter.addItem(new SingerItem("그래그래", "01099999999", R.drawable.music2));
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingerItem singerItem = (SingerItem)adapter.getItem(i);
                Toast.makeText(getApplicationContext(), singerItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.GRID_BT_GRID).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //adapter.addItem(new SingerItem(name.getText().toString(), tel.getText().toString(), R.drawable.music2));
                //adapter.notifyDataSetChanged();
            }
        });
    }

    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item){
            items.add(item);
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
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            SingerItemView view = null;
            if (convertView == null) {
                view = new SingerItemView(getApplicationContext());
            } else {
                view = (SingerItemView)convertView;
            }

            SingerItem item = items.get(i);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImageView(item.getId());
            return view;
        }
    }
}
