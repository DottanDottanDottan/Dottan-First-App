package com.example.myxbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebView_Page extends AppCompatActivity {


    private WebView webView;
    String X;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_page);


        webView = (WebView) findViewById(R.id.WebView);
        WebSettings webSettings = webView.getSettings();
        X = getIntent().getStringExtra("EXTRA_SESSION_ID");

        webView.setWebViewClient(new WebViewClient());
        webView.requestFocus();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.setSoundEffectsEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(X);

    }






    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}