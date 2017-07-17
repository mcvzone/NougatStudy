package com.example.neo.nougatstudy;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FlagmentActivity extends AppCompatActivity {
    MainFragment mainfragment;
    MenuFragment menufragment;
    ListFragment listFragment;
    ViewerFragment viewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flagment);
        mainfragment = new MainFragment();
        menufragment = new MenuFragment();
        listFragment = new ListFragment();
        viewerFragment = new ViewerFragment();

//        FragmentManager fm = getSupportFragmentManager();
//        listFragment = (ListFragment)fm.findFragmentById(R.id.FRAGMENT_FL_CONTAINER);
//        viewerFragment = (ViewerFragment)fm.findFragmentById(R.id.FRAGMENT_FL_CONTAINER2);

        TabLayout tabs = (TabLayout)findViewById(R.id.FRAGMENT_TL_TAB);
        tabs.addTab(tabs.newTab().setText("친구"));
        tabs.addTab(tabs.newTab().setText("일대일채팅"));
        tabs.addTab(tabs.newTab().setText("기타"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;

                if (position == 0) {
                    selected = mainfragment;
                    getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER, selected).commit();
                } else if (position == 1) {
                    selected = menufragment;
                    getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER, selected).commit();
                } else if (position == 2) {
                    selected = listFragment;
                    getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER, selected).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER2, viewerFragment).commit();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*
        findViewById(R.id.FRAGMENT_BT_START).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER, mainfragment).commit();
            }
        });

        findViewById(R.id.FRAGMENT_BT_START2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER, menufragment).commit();
            }
        });

        findViewById(R.id.FRAGMENT_BT_START3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER, listFragment).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER2, viewerFragment).commit();
            }
        });*/

    }


    public void onFragmentChange(int index){
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER, mainfragment).commit();
        } else if( index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER, menufragment).commit();
        }
    }

    public void onChangeImage(int index){
        getSupportFragmentManager().beginTransaction().replace(R.id.FRAGMENT_FL_CONTAINER2, viewerFragment).commit();
        viewerFragment.setImage(index);
    }
}
