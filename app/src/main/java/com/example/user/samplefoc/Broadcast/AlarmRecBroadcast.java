package com.example.user.samplefoc.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.user.samplefoc.service.notification_service;

/**
 * Created by user on 3/2/2017.
 */

public class AlarmRecBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Recccccccd00", Toast.LENGTH_LONG).show();
       Intent i_service = new Intent(context, notification_service.class);
        context.startService(i_service);
    }
}
