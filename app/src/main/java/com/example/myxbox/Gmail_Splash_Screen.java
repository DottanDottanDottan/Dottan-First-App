package com.example.myxbox;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Gmail_Splash_Screen extends Fragment {


    private static final int DelayTime = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gmail_splash_screen, container, false);


        (view.findViewById(R.id.GmailSplasScreen)).animate().setDuration(DelayTime);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Contact_Page_Gmail_And_Whatsapp()).commit();
            }
        }, DelayTime);


        return view;
    }
}