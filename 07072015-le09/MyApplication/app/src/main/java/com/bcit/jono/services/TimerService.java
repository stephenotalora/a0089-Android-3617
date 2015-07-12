package com.bcit.jono.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {
    private Timer timer;
    public TimerService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setUpTimerTask();
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    private void setUpTimerTask() {
        if (timer == null) {
            timer = new Timer();
            TimerTask ts = new TimerTask() {
                @Override
                public void run() {
                    Log.d("TimerService", "Perform some task ...");
                }
            };
            timer.schedule(ts, 0, 5000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
