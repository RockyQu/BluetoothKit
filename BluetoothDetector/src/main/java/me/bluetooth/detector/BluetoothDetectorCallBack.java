package me.bluetooth.detector;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import me.bluetooth.detector.config.BluetoothFilter;

/**
 * Callback interface used to deliver LE scan results.
 *
 * @see BluetoothDetector#startScan(BluetoothFilter, BluetoothDetectorCallBack)
 * @see BluetoothDetector#stopScan(BluetoothDetectorCallBack)
 */
public interface BluetoothDetectorCallBack {

    /**
     * Callback reporting an LE device found during a device scan initiated
     * by the {@link BluetoothAdapter#startLeScan} function.
     *
     * @param device     Identifies the remote device
     * @param rssi       The RSSI value for the remote device as reported by the
     *                   Bluetooth hardware. 0 if no RSSI value is available.
     * @param scanRecord The content of the advertisement record offered by
     *                   the remote device.
     */
    void onScan(BluetoothDevice device, int rssi, byte[] scanRecord);
}