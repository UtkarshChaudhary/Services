package com.example.lenovo.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import static java.security.AccessController.getContext;

/**
 * Created by lenovo on 25-07-2017.
 */

public class MyService extends Service {

    MediaPlayer player;
    Notification notification;
    PlayerBinder binder=new PlayerBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        player=MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        Intent notificationIntent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification= new NotificationCompat.Builder(this).setContentTitle("title").setContentText("abcd").setContentIntent(pendingIntent).build();

    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        play();
//        //return super.onStartCommand(intent, flags, startId);
//       return Service.START_NOT_STICKY
//        // if we donot want our service to restart
//    }

     @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void play(){
        if(player!=null){
            player.start();
            startForeground(100,notification);
            // if startForeground is not used then our service will not run on background
            //for showing notification ,100 is unique id,notification is Notification to be shown
        }
    }
    public class PlayerBinder extends Binder{

        public MyService getService(){
            return MyService.this;
        }
    }
    public void pause(){
        if(player!=null){
            player.pause();
            stopForeground(true);
            //true if we want to remove notification after closing of services
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pause();
        if(player!=null){
            player.release();

            player=null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

        return Service.START_NOT_STICKY;
    }
}
