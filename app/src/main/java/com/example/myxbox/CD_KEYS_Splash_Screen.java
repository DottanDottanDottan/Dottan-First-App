package com.example.myxbox;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CD_KEYS_Splash_Screen extends Fragment {

    private final static int DelayTime = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_c_d_k_e_y_s_s_p_l_a_s_h_s_c_r_e_e_n, container, false);


        (view.findViewById(R.id.CDKEYSSPLASH)).animate().setDuration(DelayTime);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new CDKEYS_Buy_GAMES()).commit();
            }
        }, DelayTime);

        return view;
    }
}