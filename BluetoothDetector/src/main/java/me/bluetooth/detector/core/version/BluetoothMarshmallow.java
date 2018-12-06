package me.bluetooth.detector.core.version;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Android 6.0 Marshmallow
 * <p>
 * 需要申请地理位置权限
 */
@TargetApi(Build.VERSION_CODES.M)
public class BluetoothMarshmallow extends BluetoothLollipop {

    @Override
    public void startScanInternal() {
        super.startScanInternal();
    }

    @Override
    public void stopScanInternal() {
        super.stopScanInternal();
    }
}