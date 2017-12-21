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
import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.core.BluetoothScanner;

import java.util.List;

/**
 * Android 5.0 Lollipop
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class BluetoothLollipop extends BluetoothJellyBean {

    private BluetoothAdapter bluetoothAdapter;

    private BluetoothDetectorCallBack callBack;

    public BluetoothLollipop() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

//    @Override
//    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
//    public void startScanInternal(BluetoothFilter filter, BluetoothDetectorCallBack callBack) {
//        Log.e("aaaa","bbb");
//        this.callBack = callBack;
//        BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
//        if (bluetoothLeScanner != null) {
//            Log.e("aaaa","cccc");
//            bluetoothLeScanner.startScan(new ScannerCallback());
//        }
//    }
//
//    @Override
//    public void stopScanInternal(BluetoothDetectorCallBack callBack) {
//
//    }

    private class ScannerCallback extends ScanCallback {

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            Log.e("aaaa","dddd");
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