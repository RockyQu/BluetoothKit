package com.tool.bluetooth.detector;

import com.tool.bluetooth.detector.config.BluetoothConfiguration;

/**
 * Android Bluetooth 4.0 Development Kit
 */
public class BluetoothDetector {

    private BluetoothConfiguration configuration;

    private IBluetoothDetectorManager bluetoothDetectorManager = null;

    private BluetoothDetector() {
        bluetoothDetectorManager = BluetoothDetectorManager.getInstance();
    }

    public void init(BluetoothConfiguration configuration) {
        if (configuration == null) {
            throw new NullPointerException();
        }

        this.configuration = configuration;

        bluetoothDetectorManager.setConfiguration(configuration);
    }

    private final static class HolderClass {
        private final static BluetoothDetector INSTANCE = new BluetoothDetector();
    }

    public static BluetoothDetector getInstance() {
        return HolderClass.INSTANCE;
    }

    public BluetoothConfiguration getConfiguration() {
        return configuration;
    }

    /**
     *
     */
    public void startScan(BluetoothDetectorCallBack callBack) {
        bluetoothDetectorManager.startScan(callBack);
    }

    /**
     *
     */
    public void stopScan(BluetoothDetectorCallBack callBack) {
        bluetoothDetectorManager.stopScan(callBack);
    }
}