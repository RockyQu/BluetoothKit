package com.tool.bluetooth.detector.core.version;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.tool.bluetooth.detector.core.BaseBluetoothScanner;

/**
 * Android 4.3 Jelly Bean
 */
public class BluetoothScannerJellyBean extends BaseBluetoothScanner implements BluetoothAdapter.LeScanCallback{

    @Override
    public void startScanInternal() {

    }

    @Override
    public void stopScanInternal() {

    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

    }
}