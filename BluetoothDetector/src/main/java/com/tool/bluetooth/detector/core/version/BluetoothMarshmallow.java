package com.tool.bluetooth.detector.core.version;

import android.annotation.TargetApi;
import android.os.Build;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;

/**
 * Android 6.0 Marshmallow
 */
@TargetApi(Build.VERSION_CODES.M)
public class BluetoothMarshmallow extends BluetoothLollipop {

    @Override
    public void startScanInternal(BluetoothDetectorCallBack callBack) {
        super.startScanInternal(callBack);
    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {
        super.stopScanInternal(callBack);
    }
}