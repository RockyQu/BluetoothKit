package com.tool.bluetooth.detector.core;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.annotation.RequiresPermission;

public class LeScanner implements BluetoothScanInternal, BluetoothAdapter.LeScanCallback {

    private BluetoothAdapter bluetoothAdapter;

    public LeScanner() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN})
    public void startScanInternal() {
        bluetoothAdapter.startLeScan(this);

    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN})
    public void stopScanInternal() {
        bluetoothAdapter.stopLeScan(this);
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

    }
}
