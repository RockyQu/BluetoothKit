package com.tool.bluetooth.detector.core;

import android.os.Build;

/**
 * Created by SZLD-PC-249 on 2017/12/13.
 */
public class BluetoothScannerCompat {

    private static BluetoothScannerCompat bluetoothScanner;

    public BluetoothScannerCompat(){

    }

    public static BluetoothScannerCompat getBluetoothScanner() {
        if (bluetoothScanner != null)
            return bluetoothScanner;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return bluetoothScanner = new BluetoothLeScannerImplMarshmallow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return bluetoothScanner = new BluetoothLeScannerImplLollipop();
        return bluetoothScanner = new BluetoothLeScannerImplJB();
    }
}
