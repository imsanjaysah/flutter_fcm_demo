package com.example.fcm_demo;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationReceiver extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("Firebase", "Message received "+remoteMessage);
        NotificationBuilder.getInstance(this).sendBundledNotification();
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("Firebase", "New Token" + s);
    }
}
