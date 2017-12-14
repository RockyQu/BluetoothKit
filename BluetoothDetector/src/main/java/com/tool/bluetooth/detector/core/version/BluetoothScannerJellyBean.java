package com.tool.bluetooth.detector.core.version;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.core.BluetoothScanner;

/**
 * Android 4.3 Jelly Bean
 */
public class BluetoothScannerJellyBean extends BluetoothScanner implements BluetoothAdapter.LeScanCallback {

    public BluetoothScannerJellyBean() {
        super();
    }

    @Override
    public void startScanInternal(BluetoothDetectorCallBack callBack) {
        Log.e("JellyBean", "BluetoothScannerJellyBean");
    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {

    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

    }
}