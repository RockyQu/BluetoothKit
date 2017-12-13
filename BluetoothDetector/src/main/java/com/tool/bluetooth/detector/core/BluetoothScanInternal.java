package com.tool.bluetooth.detector.core;

public interface BluetoothScanInternal {

    /**
     * 开启扫描，此方法与 {@link BluetoothScanInternal#stopScanInternal()} 方法应是成对调用
     */
    void startScanInternal();

    /**
     * 关闭扫描，此方法与 {@link BluetoothScanInternal#startScanInternal()} 方法应是成对调用
     */
    void stopScanInternal();
}