package com.tool.bluetooth.detector.core;

import android.os.Build;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.core.version.BluetoothScannerJellyBean;
import com.tool.bluetooth.detector.core.version.BluetoothScannerLollipop;
import com.tool.bluetooth.detector.core.version.BluetoothScannerMarshmallow;
import com.tool.bluetooth.detector.core.version.BluetoothScannerNougat;

public abstract class BluetoothScanner implements BluetoothScannerInternal {

    protected static BluetoothScanner bluetoothScanner;

    public BluetoothScanner() {

    }

    public static BluetoothScanner getBluetoothScanner() {
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
    public void startScan(BluetoothDetectorCallBack callBack) {
        startScanInternal(callBack);
    }

    @Override
    public void stopScan(BluetoothDetectorCallBack callBack) {
        stopScanInternal(callBack);
    }

    public abstract void startScanInternal(BluetoothDetectorCallBack callBack);

    public abstract void stopScanInternal(BluetoothDetectorCallBack callBack);
}
