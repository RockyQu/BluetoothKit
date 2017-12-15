package com.tool.bluetooth.detector.core;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.config.BluetoothFilter;

public interface BluetoothScannerInternal {

    /**
     * 开启扫描
     */
    void startScan(BluetoothDetectorCallBack callBack);

    /**
     * 开启扫描
     */
    void startScan(BluetoothFilter configuration, BluetoothDetectorCallBack callBack);

    /**
     * 关闭扫描
     */
    void stopScan(BluetoothDetectorCallBack callBack);
}