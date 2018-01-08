package com.tool.bluetooth.detector.core.version;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import android.os.ParcelUuid;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.core.BluetoothScanner;
import com.tool.bluetooth.detector.utils.timer.TimerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

            List<ScanFilter> filters = new ArrayList<>();
            BluetoothFilter filter = getFilter();
            if (filter != null) {
                for (String deviceName : filter.getDeviceNames()) {
                    ScanFilter builder = new ScanFilter.Builder().setDeviceName(deviceName).build();
                    filters.add(builder);
                }
                for (String deviceAddress : filter.getDeviceAddresses()) {
                    ScanFilter builder = new ScanFilter.Builder().setDeviceAddress(deviceAddress).build();
                    filters.add(builder);
                }
                for (UUID serviceUUID : filter.getServiceUUIDs()) {
                    ScanFilter builder = new ScanFilter.Builder()
                            .setServiceUuid(ParcelUuid.fromString(serviceUUID.toString())).build();
                    filters.add(builder);
                }
            }

            ScanSettings scanSettings = new ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    .build();

            bluetoothLeScanner.startScan(filters, scanSettings, scanCallback);

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
            Log.e(TAG, "onScanFailed " + errorCode);
        }
    }
}