package com.tool.bluetooth.detector.core.version;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.BluetoothDetectorHandler;
import com.tool.bluetooth.detector.utils.BluetoothUtils;

/**
 * Android 7.0 Nougat
 * <p>
 * 需要检测设备是否打开位置信息
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