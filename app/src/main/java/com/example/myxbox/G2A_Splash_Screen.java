package com.example.myxbox;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class G2A_Splash_Screen extends Fragment {

    ImageView G2ASplash;
    private static final int DelayTime = 1000;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_g2_a_splash_screen, container, false);


        (view.findViewById(R.id.G2ASplash)).animate().setDuration(DelayTime);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new G2A_BUY_GAMES()).commit();
            }
        }, DelayTime);



        return view;
    }
}