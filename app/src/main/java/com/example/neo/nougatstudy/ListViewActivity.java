package com.example.neo.nougatstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView listView = (ListView)findViewById(R.id.LISTVIEW_LV_LIST);

        SingerAdapter adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("소시", "01020102010"));
        adapter.addItem(new SingerItem("트렙", "01001991020"));
        adapter.addItem(new SingerItem("송창식", "01011199922"));
        adapter.addItem(new SingerItem("그래그래", "01099999999"));
        listView.setAdapter(adapter);
    }

    class SingerAdapter extends BaseAdapter{
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
            SingerItemView view = new SingerItemView(getApplicationContext());
            SingerItem item = items.get(i);
            view.setName(item.getName());
            view.setMobile(item.getMobile());

            return view;
        }
    }
}
