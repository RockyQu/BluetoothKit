package com.tool.bluetooth.detector.receiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 监听蓝牙状态接收器
 */
public class BluetoothReceiver extends BroadcastReceiver {

    public BluetoothReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_TURNING_ON:// 蓝牙打开中

                        break;
                    case BluetoothAdapter.STATE_ON:// 蓝牙打开完成

                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:// 蓝牙关闭中

                        break;
                    case BluetoothAdapter.STATE_OFF:// 蓝牙关闭完成

                        break;
                    default:
                        break;
                }
            default:
                break;
        }
    }
}