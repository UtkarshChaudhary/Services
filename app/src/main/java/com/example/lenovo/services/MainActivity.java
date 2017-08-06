package com.example.lenovo.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
MyService service;
    boolean bounded=false;

    Intent intent;
    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
           MyService.PlayerBinder binder=(MyService.PlayerBinder)iBinder;
            service=binder.getService();
            bounded=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
             bounded=false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent=new Intent(this,MyService.class);
    }
    public void start(View view){
         bindService(intent,serviceConnection,BIND_AUTO_CREATE);
        bounded=true;
    }

    public void stop(View view  ){
        if(bounded) {
            service.pause();
            stopService(intent);
            bounded = false;
        }
        // intent here passed must be same as passed in start func. as only same intent can stop same service
        //and we donot stop Services intent default created by android if we want to stop then we have to make our own services
    }
    public void play(View view){
       if(bounded){
           service.play();
       }
    }
    public void pause(View view){
        if(bounded){
            service.pause();
        }
    }

}
