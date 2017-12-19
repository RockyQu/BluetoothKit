package com.tool.bluetooth.detector.core;

import android.Manifest;
import android.os.Build;
import android.support.annotation.RequiresPermission;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.core.version.BluetoothJellyBean;
import com.tool.bluetooth.detector.core.version.BluetoothLollipop;
import com.tool.bluetooth.detector.core.version.BluetoothMarshmallow;
import com.tool.bluetooth.detector.core.version.BluetoothNougat;

public abstract class BluetoothScanner implements BluetoothScannerHandler {

    private static BluetoothScanner bluetoothScanner;

    public static BluetoothScannerHandler getBluetoothScanner() {
        if (bluetoothScanner != null) {
            return bluetoothScanner;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// Android 7.0 Nougat
            return bluetoothScanner = new BluetoothNougat();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// Android 6.0 Marshmallow
            return bluetoothScanner = new BluetoothMarshmallow();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// Android 5.0 Lollipop
            return bluetoothScanner = new BluetoothLollipop();
        }

        return bluetoothScanner = new BluetoothJellyBean();// Android 4.3 Jelly Bean
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScan(BluetoothDetectorCallBack callBack) {
        startScanInternal(callBack);
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScan(BluetoothFilter filter, BluetoothDetectorCallBack callBack) {
        startScanInternal(callBack);
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void stopScan(BluetoothDetectorCallBack callBack) {
        stopScanInternal(callBack);
    }

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public abstract void startScanInternal(BluetoothDetectorCallBack callBack);

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public abstract void stopScanInternal(BluetoothDetectorCallBack callBack);
}
