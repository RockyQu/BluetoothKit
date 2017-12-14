package com.tool.bluetooth.detector.core.version;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.config.BluetoothConfiguration;
import com.tool.bluetooth.detector.core.BluetoothScanner;

import java.util.List;

/**
 * Android 5.0 Lollipop
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class BluetoothLollipop extends BluetoothScanner {

    private BluetoothAdapter bluetoothAdapter;

    public BluetoothLollipop() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScanInternal(BluetoothDetectorCallBack callBack) {
        Log.e("Lollipop", "BluetoothScannerLollipop");
        BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            bluetoothLeScanner.startScan(new ScannerCallback());
        }
    }

    @Override
    public void startScan(BluetoothConfiguration configuration, BluetoothDetectorCallBack callBack) {

    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {

    }

    private class ScannerCallback extends ScanCallback {

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            Log.e("onScanResult", ""+result.getDevice().getName());


        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            Log.e("onBatchScanResults", "onBatchScanResults");
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.e("onScanFailed", ""+errorCode);
        }
    }
}