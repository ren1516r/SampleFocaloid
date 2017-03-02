package com.example.user.samplefoc.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.user.samplefoc.service.notification_service;

import java.util.logging.Logger;

/**
 * Created by user on 3/2/2017.
 */

public class AlarmRecBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String n=intent.getStringExtra("Title");

        Toast.makeText(context,"From BRD"+ intent.getStringExtra("Title"), Toast.LENGTH_LONG).show();
      //  String notftn_title=intent.getStringExtra("Title");

       Intent i_service = new Intent(context, notification_service.class);
       // i_service.putExtra("Title",notftn_title);
        context.startService(i_service);
    }
}
