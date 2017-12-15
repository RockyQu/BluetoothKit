package com.tool.bluetooth.detector;

import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.core.BluetoothScanner;
import com.tool.bluetooth.detector.core.BluetoothScannerInternal;

/**
 * Android Bluetooth 4.0 Development Kit
 *
 * @see <a href="https://github.com/DesignQu/BluetoothKit"></a>
 */
public class BluetoothDetector implements BluetoothDetectorHandler {

    private BluetoothScannerInternal bluetoothScanner;

    private final static class HolderClass {
        private final static BluetoothDetectorHandler INSTANCE = new BluetoothDetector();
    }

    public static BluetoothDetectorHandler getInstance() {
        return BluetoothDetector.HolderClass.INSTANCE;
    }

    private BluetoothDetector() {
        bluetoothScanner = BluetoothScanner.getBluetoothScanner();
    }

    @Override
    public void startScan(BluetoothDetectorCallBack callBack) {
        bluetoothScanner.startScan(callBack);
    }

    @Override
    public void startScan(BluetoothFilter configuration, BluetoothDetectorCallBack callBack) {
        bluetoothScanner.startScan(configuration, callBack);
    }

    @Override
    public void stopScan(BluetoothDetectorCallBack callBack) {
        bluetoothScanner.stopScan(callBack);
    }
}