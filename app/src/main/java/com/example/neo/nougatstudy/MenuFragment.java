package com.example.neo.nougatstudy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by neo on 2017-07-11.
 */

public class MenuFragment extends Fragment {
    FlagmentActivity activity;

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (FlagmentActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_menu, container, false);
        Button button = rootView.findViewById(R.id.FRAGMENT_BT_GOMAIN);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                activity.onFragmentChange(0);
            }
        });
        return rootView;
    }
}
