package com.tool.bluetooth.detector;

import android.Manifest;
import android.support.annotation.RequiresPermission;

import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.core.BluetoothScanner;

/**
 * ==================================================
 * Android Bluetooth 4.0 Development Kit
 *
 * @see <a href="https://github.com/DesignQu/BluetoothKit"></a>
 * ==================================================
 */
public class BluetoothDetector implements BluetoothDetectorHandler {

    private BluetoothScanner bluetoothScanner;

    private final static class HolderClass {
        private final static BluetoothDetectorHandler INSTANCE = new BluetoothDetector();
    }

    public static BluetoothDetectorHandler getInstance() {
        return BluetoothDetector.HolderClass.INSTANCE;
    }

    private BluetoothDetector() {
        bluetoothScanner = BluetoothScanner.newInstance();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScan(BluetoothFilter filter, BluetoothDetectorCallBack callBack) {
        bluetoothScanner.setFilter(filter);
        bluetoothScanner.setCallBack(callBack);
        bluetoothScanner.startScanInternal();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void stopScan() {
        bluetoothScanner.stopScanInternal();
    }

    @Override
    public boolean isScanning() {
        return bluetoothScanner.isScanning();
    }
}