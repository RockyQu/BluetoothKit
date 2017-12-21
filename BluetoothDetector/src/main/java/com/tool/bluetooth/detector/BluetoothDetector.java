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
        bluetoothScanner = BluetoothScanner.getBluetoothScanner();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScan(BluetoothDetectorCallBack callBack) {
        bluetoothScanner.startScanInternal(null, callBack);
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScan(BluetoothFilter filter, BluetoothDetectorCallBack callBack) {
        bluetoothScanner.startScanInternal(filter, callBack);
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void stopScan(BluetoothDetectorCallBack callBack) {
        bluetoothScanner.stopScanInternal(callBack);
    }
}