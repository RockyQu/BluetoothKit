package com.tool.bluetooth.detector.core.version;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.BluetoothDetectorHandler;
import com.tool.bluetooth.detector.core.BluetoothScanner;

/**
 * Android 4.3 Jelly Bean
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BluetoothJellyBean extends BluetoothScanner implements BluetoothAdapter.LeScanCallback {

    private BluetoothAdapter bluetoothAdapter;

    public BluetoothJellyBean() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void startScanInternal(BluetoothDetectorCallBack callBack) {

    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {

    }

    @Override
    public void requestCheckEach(Context context, BluetoothDetectorHandler.CheckResponse response) {
        super.requestCheckEach(context, response);
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

    }
}