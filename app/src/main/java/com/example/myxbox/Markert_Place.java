package com.example.myxbox;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Markert_Place extends Fragment {


    Markert_Place context = this;
    Button G2AB, XBOXSALE, COMINGSOON,CDKEYSB;
    String Site;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_site_buy_games, container, false);


        ((AppCompatActivity) getActivity()).getSupportActionBar().show();




        G2AB = view.findViewById(R.id.Button_G2A_Market);
        XBOXSALE = view.findViewById(R.id.Button_XboxSales);
        CDKEYSB =view.findViewById(R.id.Button_CDKEYS_Market);
        COMINGSOON = view.findViewById(R.id.Button_ComingSoon);


        G2AB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callback.setSite("https://www.g2a.com/search?query=");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new G2A_Splash_Screen()).commit();
            }
        });

        XBOXSALE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Site="https://www.xbox.com/en-US/promotions/sales/sales-and-specials";
                Intent intent = new Intent(getActivity(), WebView_Page.class);
                intent.putExtra("EXTRA_SESSION_ID", Site);
                startActivity(intent);

            }
        });


        CDKEYSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new CD_KEYS_Splash_Screen()).commit();
            }
        });


        COMINGSOON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "More Coming Soon !", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}