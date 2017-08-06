package com.example.lenovo.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService(String name) {
        super("MyIntentServices");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
           for(int i=0;i<100;i++){
               Log.d("services",i+" ");
               try {
                   Thread.sleep(1000L);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
        }
    }

}
