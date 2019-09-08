package com.example.clfp4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class AlarmReceiver extends BroadcastReceiver {

    daily4 daily4;

    public void onReceive(Context context, Intent intent)
    {
        //Toast.makeText(context,"내용을 입력하세요.", Toast.LENGTH_LONG).show();

        //daily4 = (daily4)  getContext();

/*
        daily4.textview_date.setText(daily4.getTime());
        daily4.items.clear();
        daily4.adapter.notifyDataSetChanged();*/

    }
}