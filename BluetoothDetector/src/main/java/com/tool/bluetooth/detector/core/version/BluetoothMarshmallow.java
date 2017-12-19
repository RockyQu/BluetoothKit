package com.tool.bluetooth.detector.core.version;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.BluetoothDetectorHandler;

/**
 * Android 6.0 Marshmallow
 * <p>
 * 需要申请地理位置权限
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