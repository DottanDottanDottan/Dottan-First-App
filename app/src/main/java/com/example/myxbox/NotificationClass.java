package com.example.myxbox;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

public class NotificationClass extends Application {

    public static final String NotificationID = "Notification1";

    @Override
    public void onCreate() {
        super.onCreate();


        CreateNotifications();

    }

    private void CreateNotifications() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel Notification = new NotificationChannel(
                    NotificationID,
                    "Login Successful !",
                    NotificationManager.IMPORTANCE_HIGH
            );
            Notification.setVibrationPattern(new long[] { 1000, 1000, 1000, 1000, 1000 });
            Notification.setLightColor(Color.RED);
            Notification.setDescription("Welcome To My Application !");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(Notification);
        }
    }


}
