package com.tool.bluetooth.detector.core;

import android.os.Build;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.core.version.BluetoothJellyBean;
import com.tool.bluetooth.detector.core.version.BluetoothLollipop;
import com.tool.bluetooth.detector.core.version.BluetoothMarshmallow;
import com.tool.bluetooth.detector.core.version.BluetoothNougat;

public abstract class BluetoothScanner implements BluetoothScannerInternal {

    private static BluetoothScanner bluetoothScanner;

    public static BluetoothScannerInternal getBluetoothScanner() {
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
