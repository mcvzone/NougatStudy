package com.example.neo.nougatstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by neo on 2017-07-12.
 */

public class ViewerFragment extends Fragment {

    ImageView iv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_viewer, container, false);

        iv = (ImageView)rootView.findViewById(R.id.FRAGMENT_IV_IMAGE);

        return rootView;
    }


    public void setImage(int index){
        if (index == 1) {
            iv.setImageResource(R.drawable.dream1);
        } else if (index == 2) {
            iv.setImageResource(R.drawable.dream2);
        } else if (index == 3) {
            iv.setImageResource(R.drawable.dream3);
        }
    }
}
