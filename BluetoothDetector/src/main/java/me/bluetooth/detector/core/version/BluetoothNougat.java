package me.bluetooth.detector.core.version;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Android 7.0 Nougat
 * <p>
 * 需要检测设备是否打开位置信息
 */
@TargetApi(Build.VERSION_CODES.N)
public class BluetoothNougat extends BluetoothMarshmallow {

    @Override
    public void startScanInternal() {
        super.startScanInternal();
    }

    @Override
    public void stopScanInternal() {
        super.stopScanInternal();
    }
}