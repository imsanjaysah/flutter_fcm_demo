package com.example.fcm_demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.app.NotificationChannel;
import androidx.core.app.NotificationCompat;
import com.example.fcm_demo.R;


import android.net.Uri;
import android.os.Build;
import android.util.Log;

public class NotificationBuilder {

    private  static  NotificationBuilder singleton;
    private NotificationManager notificationManager;
    private Context context;

    private NotificationBuilder(Context context, NotificationManager manager) {
        this.context = context;
        this.notificationManager = manager;

    }


    public  static NotificationBuilder  getInstance(Context context) {
        if (singleton == null) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            singleton = new NotificationBuilder(context.getApplicationContext(), notificationManager);
        }
        return singleton;
    }

    void sendBundledNotification() {
        String channelId = "default";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = buildNotification(channelId, defaultSoundUri);
        notificationManager.notify(1, notification);
        Log.d("Firebase", "Fired notification ");
        //val summary = buildSummary(message, GROUP_KEY, channelId, defaultSoundUri)
        //notificationManager.notify(SUMMARY_ID, summary)
    }

    private Notification buildNotification(String channelId, Uri defaultSoundUri) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        //notificationIntent.putExtra("tankId", message.tankId)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        /*val icon = BitmapFactory.decodeResource(context.resources,
                R.drawable.ic_app_logo)*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "fcm_test";/*context.getString(R.string.default_notification_channel_name)*/// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId, name, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        return new NotificationCompat.Builder(context, channelId)
                .setContentTitle("Hi")
                .setContentText("From Fcm")
                //.setWhen(0)
                .setSmallIcon(R.mipmap.ic_launcher)
                //.setLargeIcon(icon)
                //.setShowWhen(true)
                //.setGroup(groupKey)
                //.setStyle(NotificationCompat.BigTextStyle().bigText(message.msg))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(1)
                .setAutoCancel(true)

                .build();
    }
}
