package me.bluetooth.detector.core.version;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import android.os.ParcelUuid;

import androidx.annotation.RequiresPermission;
import me.bluetooth.detector.facade.BluetoothDetectorCallBack;
import me.bluetooth.detector.config.BluetoothFilter;
import me.bluetooth.detector.utils.LogDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Android 5.0 Lollipop
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class BluetoothLollipop extends BluetoothJellyBean {

    private static final String TAG = BluetoothLollipop.class.getSimpleName();

    private BluetoothLeScanner bluetoothLeScanner;
    private ScanCallback scanCallback;

    public BluetoothLollipop() {
        bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public void startScanInternal() {
        if (!isScanning) {
            scanCallback = new ScannerCallback(getCallBack());

            List<ScanFilter> filters = new ArrayList<>();
            BluetoothFilter filter = getFilter();
            if (filter != null) {
                for (String deviceName : filter.getDeviceNames()) {
                    ScanFilter builder = new ScanFilter.Builder().setDeviceName(deviceName).build();
                    filters.add(builder);
                }
                for (String deviceAddress : filter.getDeviceAddresses()) {
                    ScanFilter builder = new ScanFilter.Builder().setDeviceAddress(deviceAddress).build();
                    filters.add(builder);
                }
                for (UUID serviceUUID : filter.getServiceUUIDs()) {
                    ScanFilter builder = new ScanFilter.Builder()
                            .setServiceUuid(ParcelUuid.fromString(serviceUUID.toString())).build();
                    filters.add(builder);
                }
            }

            int scanMode;
            switch (filter.getScanMode()) {
                case BluetoothFilter.SCAN_MODE_LOW_POWER:
                    scanMode = ScanSettings.SCAN_MODE_LOW_POWER;
                    break;
                case BluetoothFilter.SCAN_MODE_BALANCED:
                    scanMode = ScanSettings.SCAN_MODE_BALANCED;
                    break;
                case BluetoothFilter.SCAN_MODE_LOW_LATENCY:
                    scanMode = ScanSettings.SCAN_MODE_LOW_LATENCY;
                    break;
                default:
                    scanMode = ScanSettings.SCAN_MODE_BALANCED;
                    break;
            }

            ScanSettings scanSettings = new ScanSettings.Builder()
                    .setScanMode(scanMode)
                    .build();

            bluetoothLeScanner.startScan(filters, scanSettings, scanCallback);

            isScanning = true;
        }
    }

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    @Override
    public void stopScanInternal() {
        if (isScanning) {
            bluetoothLeScanner.stopScan(scanCallback);

            isScanning = false;
        }
    }

    private class ScannerCallback extends ScanCallback {

        private BluetoothDetectorCallBack callBack;

        public ScannerCallback(BluetoothDetectorCallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
//            LogDetector.e(getFilter().isDebug(), "address " + result.getDevice().getAddress() + " name " + result.getDevice().getName() + " rssi " + result.getRssi());

            if (callBack != null) {
                callBack.onScan(result.getDevice(), result.getRssi(), result.getScanRecord() != null ? result.getScanRecord().getBytes() : null);
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            LogDetector.e(getFilter().isDebug(), "onScanFailed errorCode " + errorCode);

            if (callBack != null) {
                callBack.onFailed(errorCode);
            }
        }
    }
}