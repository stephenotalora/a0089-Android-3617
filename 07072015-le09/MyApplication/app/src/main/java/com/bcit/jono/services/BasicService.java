package com.bcit.jono.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class BasicService extends Service {
    public BasicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String msg = String.format("onCreate() Thread ID: %d", android.os.Process.myTid());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String msg = String.format("onStartCommand() Thread ID: %d", android.os.Process.myTid());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        //return super.onStartCommand(intent, flags, startId);

        if (intent != null) {
            int a = intent.getIntExtra("a", 0);
            int b = intent.getIntExtra("b", 0);
            Toast.makeText(this, "Total is: " + (a+b), Toast.LENGTH_LONG).show();
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        String msg = String.format("onDestroy() Thread ID: %d", android.os.Process.myTid());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
}
