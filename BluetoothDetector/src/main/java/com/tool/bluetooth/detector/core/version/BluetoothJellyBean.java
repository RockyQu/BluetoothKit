package com.tool.bluetooth.detector.core.version;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Build;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.core.BluetoothScanner;

/**
 * Android 4.3 Jelly Bean
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BluetoothJellyBean extends BluetoothScanner implements BluetoothAdapter.LeScanCallback {

    private BluetoothAdapter bluetoothAdapter;

    private BluetoothDetectorCallBack callBack;

    public BluetoothJellyBean() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void startScanInternal(BluetoothFilter filter, BluetoothDetectorCallBack callBack) {
        this.callBack = callBack;
        bluetoothAdapter.startLeScan(this);
    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {
        bluetoothAdapter.stopLeScan(this);
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        if (callBack != null) {
            callBack.onScan(device, rssi, scanRecord);
        }
    }
}