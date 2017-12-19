package com.tool.bluetooth.detector.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * 网络状态变化接收器
 */
public class BluetoothReceiver extends BroadcastReceiver {

    public BluetoothReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("onReceive", "" + intent.getAction());
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

        }
    }
}