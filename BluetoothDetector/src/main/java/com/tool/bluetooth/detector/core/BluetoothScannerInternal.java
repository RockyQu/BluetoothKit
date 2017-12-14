package com.tool.bluetooth.detector.core;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;

public interface BluetoothScannerInternal {

    /**
     * 开启扫描
     */
    void startScan(BluetoothDetectorCallBack callBack);

    /**
     * 关闭扫描
     */
    void stopScan(BluetoothDetectorCallBack callBack);
}