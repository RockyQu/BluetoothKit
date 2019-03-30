package me.bluetooth.detector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

public interface BluetoothStatusListener {

    /**
     * 正在扫描状态
     */
    int SCAN_STATUS_START = 8;

    /**
     * 停止扫描状态
     */
    int SCAN_STATUS_STOP = 9;

    @IntDef({SCAN_STATUS_START, SCAN_STATUS_STOP})
    @Retention(RetentionPolicy.SOURCE)
    @interface ScanStatus {
    }

    /**
     * 设备为打开状态
     */
    int DEVICE_STATUS_START = 11;

    /**
     * 设备为关闭状态
     */
    int DEVICE_STATUS_STOP = 12;

    @IntDef({DEVICE_STATUS_START, DEVICE_STATUS_STOP})
    @Retention(RetentionPolicy.SOURCE)
    @interface DeviceStatus {
    }

    /**
     * 扫描状态，当前扫描状态发生变化时回调此方法
     *
     * @param status
     * @see #SCAN_STATUS_START
     * @see #SCAN_STATUS_STOP
     */
    void onScanStatus(@ScanStatus int status);

    /**
     * 设备状态/蓝牙状态，当前设备状态发生变化时回调此方法
     *
     * @param status
     * @see #DEVICE_STATUS_START
     * @see #DEVICE_STATUS_STOP
     */
    void onDeviceStatus(@DeviceStatus int status);
}