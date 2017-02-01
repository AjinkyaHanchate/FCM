package in.nfluence.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.NotificationCompat;
import android.os.Handler;

import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by ${ajinkya} on ${2016-04-04}.
 */
public class MyFcmListenerService extends FirebaseMessagingService {
    private static final String TAG = "MyFcmListenerService";

    @Override

    public void onMessageReceived(RemoteMessage message){
        //String from = message.getFrom();
        Map data = message.getData();
        String messagenote = data.get("message").toString();
        String title = data.get("title").toString();
        String tickerText = data.get("tickerText").toString();
        String subtitle = data.get("subtitle").toString();
       long when= Calendar.getInstance().getTimeInMillis();


        NotificationManager notificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
      //  Intent intent=new Intent(this,ShowNote.class);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("ms", messagenote);
        notificationIntent.putExtras(bundle);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationIntent.setData(Uri.parse("content://" + when));
        PendingIntent intent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
     //  startActivity(notificationIntent);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.icon)
                .setContentTitle(title)
                 .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setTicker(tickerText)
                .setSubText(subtitle)
                .setContentIntent(intent)
                .setAutoCancel(true)
                .setContentText(messagenote);


        notificationManager.notify((int)when, mBuilder.build());
         //want to show notifaction? then uncomment this and comment on  startActivity(notificationIntent); also change the activity name to which open onclik on notification





    }


    
}
