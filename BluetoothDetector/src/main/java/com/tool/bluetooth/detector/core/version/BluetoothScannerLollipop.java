package com.tool.bluetooth.detector.core.version;

import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.core.BluetoothScanner;

/**
 * Android 5.0 Lollipop
 */
public class BluetoothScannerLollipop extends BluetoothScanner {

    @Override
    public void startScanInternal(BluetoothDetectorCallBack callBack) {
        Log.e("Lollipop", "BluetoothScannerLollipop");
    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {

    }
}