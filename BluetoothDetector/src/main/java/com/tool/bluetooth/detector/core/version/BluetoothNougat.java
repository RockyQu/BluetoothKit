package com.tool.bluetooth.detector.core.version;

import android.annotation.TargetApi;
import android.os.Build;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;

/**
 * Android 7.0 Nougat
 */
@TargetApi(Build.VERSION_CODES.N)
public class BluetoothNougat extends BluetoothMarshmallow {

    @Override
    public void startScanInternal(BluetoothDetectorCallBack callBack) {
        super.startScanInternal(callBack);
    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {
        super.stopScanInternal(callBack);
    }
}