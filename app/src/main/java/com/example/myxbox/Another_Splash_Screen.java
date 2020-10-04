package com.example.myxbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Another_Splash_Screen extends AppCompatActivity {
    private static final int DelayTime = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_splash_screen);
       (findViewById(R.id.LogoScreen)).animate().rotation(360f).setDuration(DelayTime);
        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(Another_Splash_Screen.this, InfoAboutApp.class));
            Another_Splash_Screen.this.finish();
        }
    }, DelayTime);

    }

    }
