package com.tool.bluetooth.detector.core;

public interface BluetoothScanInternal {

    /**
     * 开启扫描，此方法与 {@link BluetoothScanInternal#stopScan()} 方法应是成对调用
     */
    void startScan();

    /**
     * 关闭扫描，此方法与 {@link BluetoothScanInternal#startScan()} 方法应是成对调用
     */
    void stopScan();
}