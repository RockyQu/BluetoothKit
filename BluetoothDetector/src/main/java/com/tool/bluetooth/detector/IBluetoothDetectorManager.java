package com.tool.bluetooth.detector;


import com.tool.bluetooth.detector.config.BluetoothConfiguration;

public interface IBluetoothDetectorManager {

    void startScan(BluetoothDetectorCallBack callBack);

    void stopScan(BluetoothDetectorCallBack callBack);

    void setConfiguration(BluetoothConfiguration configuration);
}