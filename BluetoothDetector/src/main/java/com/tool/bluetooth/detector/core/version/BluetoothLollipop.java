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
import com.tool.bluetooth.detector.utils.timer.TimerManager;

import java.util.List;

/**
 * Android 5.0 Lollipop
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class BluetoothLollipop extends BluetoothJellyBean {

    private static final String TAG = BluetoothLollipop.class.getSimpleName();

    private BluetoothLeScanner bluetoothLeScanner;
    private ScanCallback scanCallback;

    public BluetoothLollipop() {
        bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScanInternal() {
        if (!isScanning) {
            scanCallback = new ScannerCallback(getCallBack());
            bluetoothLeScanner.startScan(scanCallback);

            isScanning = true;
        }
    }

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    @Override
    public void stopScanInternal() {
        if (isScanning) {
            bluetoothLeScanner.stopScan(scanCallback);

            isScanning = false;
        }
    }

    private class ScannerCallback extends ScanCallback {

        private BluetoothDetectorCallBack callBack;

        public ScannerCallback(BluetoothDetectorCallBack callBack) {
            this.callBack = callBack;
        }

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