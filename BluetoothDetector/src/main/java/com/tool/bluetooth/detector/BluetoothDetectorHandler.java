package com.tool.bluetooth.detector;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import com.tool.bluetooth.detector.config.BluetoothConfiguration;

public interface BluetoothDetectorHandler {

    /**
     * 开启扫描，并回调至 {@link BluetoothDetectorCallBack#onScan(BluetoothDevice, int, byte[])} 方法
     * 此方法调用请放在 {@link android.app.Activity#onCreate(Bundle)} 方法里执行
     * 与 {@link BluetoothDetectorHandler#stopScan(BluetoothDetectorCallBack)} 方法应是成对出现
     *
     * @param callBack
     */
    void startScan(BluetoothDetectorCallBack callBack);

    /**
     * 关闭扫描，并移除回调
     * 此方法调用请放在 {@link Activity#onDestroy()} 方法里执行
     * 与 {@link BluetoothDetectorHandler#startScan(BluetoothDetectorCallBack)} 方法应是成对出现
     *
     * @param callBack
     */
    void stopScan(BluetoothDetectorCallBack callBack);

    /**
     * 设置一些扫描参数
     *
     * @param configuration
     * @see BluetoothConfiguration#getServiceUUIDs()
     * @see BluetoothConfiguration#getDeviceNames()
     * @see BluetoothConfiguration#getDeviceMacs()
     */
    void setConfiguration(BluetoothConfiguration configuration);
}