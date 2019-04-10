package me.bluetooth.detector.facade;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import me.bluetooth.detector.config.BluetoothFilter;

public interface BluetoothDetectorHandler {

    /**
     * 开启扫描，并回调至 {@link BluetoothDetectorCallBack#onScan(BluetoothDevice, int, byte[])} 方法
     * 此方法调用请放在 {@link android.app.Activity#onCreate(Bundle)} 方法里执行
     * 与 {@link BluetoothDetectorHandler#stopScan()} 方法应是成对出现
     *
     * @param filter
     * @param callBack
     */
    void startScan(BluetoothFilter filter, BluetoothDetectorCallBack callBack);

    /**
     * 关闭扫描，并移除回调
     * 此方法调用请放在 {@link Activity#onDestroy()} 方法里执行
     * 与 {@link BluetoothDetectorHandler#startScan(BluetoothFilter, BluetoothDetectorCallBack)} 方法应是成对出现
     */
    void stopScan();

    /**
     * 是否正在扫描设备
     *
     * @return
     */
    boolean isScanning();
}