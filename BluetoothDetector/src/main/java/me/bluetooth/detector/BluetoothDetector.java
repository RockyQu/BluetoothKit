package me.bluetooth.detector;

import android.Manifest;

import androidx.annotation.RequiresPermission;
import me.bluetooth.detector.config.BluetoothFilter;
import me.bluetooth.detector.core.BluetoothScanner;

/**
 * {@link BluetoothDetector} 里只有蓝牙扫描操作，不包含连接、数据交互
 *
 * @see <a href="https://github.com/RockyQu/BluetoothKit"></a>
 */
public class BluetoothDetector implements BluetoothDetectorHandler {

    private BluetoothScanner bluetoothScanner;

    private final static class HolderClass {
        private final static BluetoothDetectorHandler INSTANCE = new BluetoothDetector();
    }

    public static BluetoothDetectorHandler getInstance() {
        return BluetoothDetector.HolderClass.INSTANCE;
    }

    private BluetoothDetector() {
        bluetoothScanner = BluetoothScanner.newInstance();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScan(BluetoothFilter filter, BluetoothDetectorCallBack callBack) {
        bluetoothScanner.setFilter(filter);
        bluetoothScanner.setCallBack(callBack);
        bluetoothScanner.startScanInternal();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void stopScan() {
        bluetoothScanner.stopScanInternal();
    }

    @Override
    public boolean isScanning() {
        return bluetoothScanner.isScanning();
    }
}