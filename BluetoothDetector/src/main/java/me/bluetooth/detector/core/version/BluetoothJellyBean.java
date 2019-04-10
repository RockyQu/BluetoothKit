package me.bluetooth.detector.core.version;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Build;

import androidx.annotation.RequiresPermission;
import me.bluetooth.detector.facade.BluetoothDetectorCallBack;
import me.bluetooth.detector.core.BluetoothScanner;
import me.bluetooth.detector.utils.LogDetector;

/**
 * Android 4.3 Jelly Bean
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BluetoothJellyBean extends BluetoothScanner implements BluetoothAdapter.LeScanCallback {

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDetectorCallBack callBack;

    public BluetoothJellyBean() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @SuppressWarnings("deprecation")
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    @Override
    public void startScanInternal() {
        if (!isScanning) {
            this.callBack = getCallBack();
            bluetoothAdapter.startLeScan(this);

            isScanning = true;
        }
    }

    @SuppressWarnings("deprecation")
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    @Override
    public void stopScanInternal() {
        if (isScanning) {
            bluetoothAdapter.stopLeScan(this);

            isScanning = false;
        }
    }

    @Override
    public boolean isScanning() {
        return isScanning;
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        LogDetector.e(getFilter().isDebug(), "address " + device.getAddress() + " name " + device.getName() + " rssi " + rssi);

        if (callBack != null) {
            callBack.onScan(device, rssi, scanRecord);
        }
    }
}