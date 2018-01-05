package com.tool.bluetooth.detector.core.version;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.support.annotation.RequiresPermission;

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

    @SuppressWarnings("deprecation")
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    @Override
    public void startScanInternal() {
        if(!isScanning){
            this.callBack = getCallBack();
            bluetoothAdapter.startLeScan(this);

            isScanning = true;
        }
    }

    @SuppressWarnings("deprecation")
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    @Override
    public void stopScanInternal() {
        if(isScanning){
            bluetoothAdapter.stopLeScan(this);

            isScanning = false;
        }
    }

    @Override
    public boolean isScanning() {
        return isScanning;
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        if (callBack != null) {
            callBack.onScan(device, rssi, scanRecord);
        }
    }
}