package com.tool.bluetooth.detector.core.version;

import android.annotation.TargetApi;
import android.os.Build;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.config.BluetoothFilter;

/**
 * Android 6.0 Marshmallow
 * <p>
 * 需要申请地理位置权限
 */
@TargetApi(Build.VERSION_CODES.M)
public class BluetoothMarshmallow extends BluetoothLollipop {

    @Override
    public void startScanInternal(BluetoothFilter filter, BluetoothDetectorCallBack callBack) {
        super.startScanInternal(filter, callBack);
    }

    @Override
    public void stopScanInternal() {
        super.stopScanInternal();
    }
}