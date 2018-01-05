package com.tool.bluetooth.detector.core;

import android.os.Build;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.core.version.BluetoothJellyBean;
import com.tool.bluetooth.detector.core.version.BluetoothLollipop;
import com.tool.bluetooth.detector.core.version.BluetoothMarshmallow;
import com.tool.bluetooth.detector.core.version.BluetoothNougat;

public abstract class BluetoothScanner{

    private static final String TAG = BluetoothScanner.class.getSimpleName();

    private BluetoothFilter filter;
    private BluetoothDetectorCallBack callBack;

    protected boolean isScanning;

    /**
     * 由于各 Android 版本对蓝牙处理不同，这里做一个 Android 版本差异化处理
     *
     * @see BluetoothNougat
     * @see BluetoothMarshmallow
     * @see BluetoothLollipop
     * @see BluetoothJellyBean
     */
    public static BluetoothScanner newInstance() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {// Android 7.0 Nougat
            return new BluetoothNougat();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// Android 6.0 Marshmallow
            return new BluetoothMarshmallow();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// Android 5.0 Lollipop
            return new BluetoothLollipop();
        }

        return new BluetoothJellyBean();// Android 4.3 Jelly Bean
    }

    public BluetoothFilter getFilter() {
        return filter;
    }

    public void setFilter(BluetoothFilter filter) {
        this.filter = filter;
    }

    public BluetoothDetectorCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(BluetoothDetectorCallBack callBack) {
        this.callBack = callBack;
    }

    public abstract void startScanInternal();

    public abstract void stopScanInternal();

    public abstract boolean isScanning();
}
