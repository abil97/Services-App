package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class MyService extends IntentService {

    private static final String TAG = "Thread";
    private int counter = 0;
    private boolean run = true;


    public MyService() {
        super("MyService");
    }


    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(MyService.this, "The Service has Started", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        run = false;
        Toast.makeText(MyService.this, "The Service is Destroyed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void onHandleIntent(Intent intent) {

        Intent counterSender = new Intent();
        counterSender.setAction("com.example.SendBroadcast");

        while(run) {
            try {
                counter -=- 1;
                counterSender.putExtra("MyCounter", Integer.toString(counter));
                sendBroadcast(counterSender);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "Hello from the Worker Thread");
        }
    }
}
