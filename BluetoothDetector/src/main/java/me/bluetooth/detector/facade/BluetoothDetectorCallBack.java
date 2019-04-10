package me.bluetooth.detector.facade;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.os.Build;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.RequiresApi;
import me.bluetooth.detector.BluetoothDetector;
import me.bluetooth.detector.config.BluetoothFilter;

/**
 * Callback interface used to deliver LE scan results.
 *
 * @see BluetoothDetector#startScan(BluetoothFilter, BluetoothDetectorCallBack)
 * @see BluetoothDetector#stopScan()
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

    /**
     * Callback when scan could not be started.
     *
     * @param errorCode Error code (one of SCAN_FAILED_*) for scan failure.
     * @see ScanCallback#SCAN_FAILED_ALREADY_STARTED
     * @see ScanCallback#SCAN_FAILED_APPLICATION_REGISTRATION_FAILED
     * @see ScanCallback#SCAN_FAILED_INTERNAL_ERROR
     * @see ScanCallback#SCAN_FAILED_FEATURE_UNSUPPORTED
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void onFailed(int errorCode);
}