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

    private static final String TAG = BluetoothLollipop.class.getSimpleName();

    private BluetoothLeScanner bluetoothLeScanner;
    private BluetoothDetectorCallBack callBack;

    public BluetoothLollipop() {
        bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScanInternal(BluetoothFilter filter, BluetoothDetectorCallBack callBack) {
        this.callBack = callBack;
        if (bluetoothLeScanner != null) {
            bluetoothLeScanner.startScan(new ScannerCallback());
        }
    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {
        this.callBack = callBack;
        if (bluetoothLeScanner != null) {
            bluetoothLeScanner.stopScan(new ScannerCallback());
        }
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
            Log.e(TAG, "onBatchScanResults");
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.e(TAG, "" + errorCode);
        }
    }
}