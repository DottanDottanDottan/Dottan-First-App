package com.example.myxbox;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class What_IS_New extends Fragment {

    private WebView webView;
    String X="https://www.gamespot.com/news/";
    Button CloseButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_what__i_s__new, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide(); ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        CloseButton= view.findViewById(R.id.Btn_Close_What_Is_New);
        CloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile_Fragment()).commit();
            }
        });




        webView = (WebView) view.findViewById(R.id.WebView_On_Gaming);
        WebSettings webSettings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient());
        webView.requestFocus();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.setSoundEffectsEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(X);



        return view;
    }

//    @Override
//    public void onBackPressed() {
//        if(webView.canGoBack()){
//            webView.goBack();
//       }
//        else{
//            super.onBackPressed();
//        }
//    }

}