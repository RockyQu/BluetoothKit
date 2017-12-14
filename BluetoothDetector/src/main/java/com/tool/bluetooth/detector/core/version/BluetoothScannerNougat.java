package com.tool.bluetooth.detector.core.version;

import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;

/**
 * Android 7.0 Nougat
 */
public class BluetoothScannerNougat extends BluetoothScannerMarshmallow {

    @Override
    public void startScanInternal(BluetoothDetectorCallBack callBack) {
        super.startScanInternal(callBack);
        Log.e("Nougat", "BluetoothScannerNougat");
    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {
        super.stopScanInternal(callBack);
    }
}