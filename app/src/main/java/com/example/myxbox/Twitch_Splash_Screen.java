package com.example.myxbox;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Twitch_Splash_Screen extends Fragment {

    private static final int DelayTime = 1000;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_twitch_splash_screen, container, false);

        (view.findViewById(R.id.TwitchSplash)).animate().setDuration(DelayTime);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Stream_Channel()).commit();
            }
        }, DelayTime);


        return view;
    }
}