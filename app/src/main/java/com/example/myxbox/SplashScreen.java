package com.example.myxbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SplashScreen extends AppCompatActivity {
    Context context = this;
    int Counter = 0;
    String strProgress;
    ProgressBar progressBar;
    int SPLASH_TIME = 6000; //This is 6 seconds
    private static final int DelayTime = 5000;
    ImageView imageView;
    TextView Percent, Welcome;
    private ScheduledThreadPoolExecutor listUpdaterExecuter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Welcome = (TextView) findViewById(R.id.WelcomeString);
        Percent = (TextView) findViewById(R.id.PercentProgressBar);
        imageView = (ImageView) findViewById(R.id.XboxLogo);
        imageView.animate().rotation(360f).setDuration(DelayTime);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setColorFilter(Color.WHITE);
                Welcome.setTextColor(Color.WHITE);
            }
        }, DelayTime);


        progressBar = findViewById(R.id.splashProgress);

        playProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mySuperIntent = new Intent(SplashScreen.this, InfoAboutApp.class);
                startActivity(mySuperIntent);

                //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                finish();

            }
        }, SPLASH_TIME);
    }


    private void playProgress() {
        listUpdaterExecuter = new ScheduledThreadPoolExecutor(2);
        listUpdaterExecuter.scheduleAtFixedRate(new Runnable() {
            private Runnable update = new Runnable() {
                @Override
                public void run() {
                    Counter++;
                    progressBar.setProgress(Counter);
                    Percent.setText(progressBar.getProgress()+"%");
                    if (Counter == 100) {
                        return;
                    }
                }
            };

            @Override
            public void run() {
                runOnUiThread(update);
            }
        }, 0, 50, TimeUnit.MILLISECONDS);


    }

}

