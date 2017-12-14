package com.tool.bluetooth.detector.core.version;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.config.BluetoothConfiguration;
import com.tool.bluetooth.detector.core.BluetoothScanner;

/**
 * Android 4.3 Jelly Bean
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BluetoothJellyBean extends BluetoothScanner implements BluetoothAdapter.LeScanCallback {

    public BluetoothJellyBean() {
        super();
    }

    @Override
    public void startScanInternal(BluetoothDetectorCallBack callBack) {
        Log.e("JellyBean", "BluetoothScannerJellyBean");
    }

    @Override
    public void startScan(BluetoothConfiguration configuration, BluetoothDetectorCallBack callBack) {

    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {

    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

    }
}