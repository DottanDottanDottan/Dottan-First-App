package com.example.myxbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class InfoAboutApp extends AppCompatActivity {


    ViewFlipper V_Flipper;

    public void FlipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        V_Flipper.addView(imageView);
        V_Flipper.setFlipInterval(4000);
        V_Flipper.setAutoStart(true);

        V_Flipper.setInAnimation(this, android.R.anim.slide_in_left);
        V_Flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_about_app);




        V_Flipper = findViewById(R.id.V_Flipper);

        int Images[] = {R.drawable.gallerygames0, R.drawable.gallerygames1, R.drawable.gallerygames2,
                R.drawable.gallerygames3, R.drawable.gallerygames4, R.drawable.gallerygames5, R.drawable.gallerygames6,
                R.drawable.gallerygames7, R.drawable.gallerygames8, R.drawable.gallerygames9, R.drawable.gallerygames10};

        for (int i = 0; i < Images.length; i++) {
            FlipperImages(Images[i]);
        }

        final CircularProgressButton btn = (CircularProgressButton) findViewById(R.id.btn_id);
        final AsyncTask<String, Integer, String> task = new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                for (int i = 0; i <= 100; i++) {
                    publishProgress(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                return "yes";
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                btn.setProgress(values[0]);
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String s) {
                if (s.equals("yes")) {
                    Bitmap myLogo = BitmapFactory.decodeResource(getResources(),R.drawable.fullwhitecheckmark);
                    btn.doneLoadingAnimation(R.color.colorPrimary,myLogo);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent I = new Intent(InfoAboutApp.this, Login_Page.class);
                    startActivity(I);
                }
                super.onPostExecute(s);
            }
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.startAnimation();
                task.execute();
            }
        });
    }
}
