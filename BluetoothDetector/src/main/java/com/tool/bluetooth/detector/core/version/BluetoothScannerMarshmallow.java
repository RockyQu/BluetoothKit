package com.tool.bluetooth.detector.core.version;

import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;

/**
 * Android 6.0 Marshmallow
 */
public class BluetoothScannerMarshmallow extends BluetoothScannerLollipop {

    @Override
    public void startScanInternal(BluetoothDetectorCallBack callBack) {
        super.startScanInternal(callBack);
        Log.e("Marshmallow", "BluetoothScannerMarshmallow");
    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {
        super.stopScanInternal(callBack);
    }
}