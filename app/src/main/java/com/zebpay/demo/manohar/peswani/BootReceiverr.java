package com.zebpay.demo.manohar.peswani;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.zebpay.demo.manohar.peswani.database.MarketService;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Manohar on 04-08-2017.
 */

public class BootReceiverr extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent data) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, MarketService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(),
                5 * 60 * 1000,
                pendingIntent);
    }
}
