package com.example.neo.nougatstudy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by neo on 2017-07-12.
 */

public class ListFragment extends Fragment {
    FlagmentActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (FlagmentActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_list, container, false);

        rootView.findViewById(R.id.FRAGMENT_BT_INTERFACE1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onChangeImage(1);
            }
        });

        rootView.findViewById(R.id.FRAGMENT_BT_INTERFACE2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onChangeImage(2);
            }
        });

        rootView.findViewById(R.id.FRAGMENT_BT_INTERFACE3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onChangeImage(3);
            }
        });
        return rootView;
    }
}
