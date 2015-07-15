package com.bcit.jono.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootCompletedRx extends BroadcastReceiver {
    public BootCompletedRx() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // to emulate and fire the event use adb shell
        // am broadcast -a android.intent.action.BOOT_COMPLETED -p com.bcit.jono.broadcastreceiverdemo
        Toast.makeText(context, "Boot completed event", Toast.LENGTH_LONG).show();
        // context.start
    }
}
