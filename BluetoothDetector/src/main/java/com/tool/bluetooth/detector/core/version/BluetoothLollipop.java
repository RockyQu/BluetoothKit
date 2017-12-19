package com.tool.bluetooth.detector.core.version;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.BluetoothDetectorHandler;
import com.tool.bluetooth.detector.core.BluetoothScanner;

import java.util.List;

/**
 * Android 5.0 Lollipop
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class BluetoothLollipop extends BluetoothScanner {

    private BluetoothAdapter bluetoothAdapter;

    private BluetoothDetectorCallBack callBack;

    public BluetoothLollipop() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScanInternal(BluetoothDetectorCallBack callBack) {
        this.callBack = callBack;
        BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            bluetoothLeScanner.startScan(new ScannerCallback());
        }
    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {

    }

    private class ScannerCallback extends ScanCallback {

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            if (callBack != null) {
                callBack.onScan(result.getDevice(), result.getRssi(), result.getScanRecord() != null ? result.getScanRecord().getBytes() : null);
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            Log.e("onBatchScanResults", "onBatchScanResults");
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.e("onScanFailed", "" + errorCode);
        }
    }
}