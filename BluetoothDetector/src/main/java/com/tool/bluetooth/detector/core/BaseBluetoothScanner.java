package com.tool.bluetooth.detector.core;

import android.Manifest;
import android.os.Build;
import android.support.annotation.RequiresPermission;

import com.tool.bluetooth.detector.core.version.BluetoothScannerJellyBean;
import com.tool.bluetooth.detector.core.version.BluetoothScannerLollipop;
import com.tool.bluetooth.detector.core.version.BluetoothScannerMarshmallow;
import com.tool.bluetooth.detector.core.version.BluetoothScannerNougat;

public abstract class BaseBluetoothScanner implements BluetoothScanInternal {

    private static BaseBluetoothScanner bluetoothScanner;

    public BaseBluetoothScanner() {

    }

    public static BaseBluetoothScanner getBluetoothScanner() {
        if (bluetoothScanner != null) {
            return bluetoothScanner;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// Android 7.0 Nougat
            return bluetoothScanner = new BluetoothScannerNougat();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// Android 6.0 Marshmallow
            return bluetoothScanner = new BluetoothScannerMarshmallow();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// Android 5.0 Lollipop
            return bluetoothScanner = new BluetoothScannerLollipop();
        }

        return bluetoothScanner = new BluetoothScannerJellyBean();// Android 4.3 Jelly Bean
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScan() {

        startScanInternal();
    }

    @Override
    @RequiresPermission(Manifest.permission.BLUETOOTH_ADMIN)
    public void stopScan() {

        stopScanInternal();
    }

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public abstract void startScanInternal();

    @RequiresPermission(Manifest.permission.BLUETOOTH_ADMIN)
    public abstract void stopScanInternal();
}
