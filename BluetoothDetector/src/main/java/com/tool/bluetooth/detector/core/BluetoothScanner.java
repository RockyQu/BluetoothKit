package com.tool.bluetooth.detector.core;

import android.Manifest;
import android.os.Build;
import android.support.annotation.RequiresPermission;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.core.version.BluetoothJellyBean;
import com.tool.bluetooth.detector.core.version.BluetoothLollipop;
import com.tool.bluetooth.detector.core.version.BluetoothMarshmallow;
import com.tool.bluetooth.detector.core.version.BluetoothNougat;

public abstract class BluetoothScanner {

    private static BluetoothScanner bluetoothScanner;

    /**
     * 由于各 Android 版本对蓝牙处理不同，这里做一个 Android 版本差异化处理
     *
     * @see BluetoothNougat
     * @see BluetoothMarshmallow
     * @see BluetoothLollipop
     * @see BluetoothJellyBean
     */
    public static BluetoothScanner getBluetoothScanner() {
        if (bluetoothScanner != null) {
            return bluetoothScanner;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// Android 7.0 Nougat
            return bluetoothScanner = new BluetoothNougat();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// Android 6.0 Marshmallow
            return bluetoothScanner = new BluetoothMarshmallow();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// Android 5.0 Lollipop
            return bluetoothScanner = new BluetoothLollipop();
        }

        return bluetoothScanner = new BluetoothJellyBean();// Android 4.3 Jelly Bean
    }

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public abstract void startScanInternal(BluetoothFilter filter, BluetoothDetectorCallBack callBack);

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH})
    public abstract void stopScanInternal(BluetoothDetectorCallBack callBack);
}
