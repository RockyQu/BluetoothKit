package com.tool.bluetooth.detector;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.widget.Toast;

import com.tool.bluetooth.detector.config.BluetoothFilter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface BluetoothDetectorHandler {

    /**
     * 开启扫描，并回调至 {@link BluetoothDetectorCallBack#onScan(BluetoothDevice, int, byte[])} 方法
     * 此方法调用请放在 {@link android.app.Activity#onCreate(Bundle)} 方法里执行
     * 与 {@link BluetoothDetectorHandler#stopScan(BluetoothDetectorCallBack)} 方法应是成对出现
     *
     * @param filter
     * @param callBack
     */
    void startScan(BluetoothFilter filter, BluetoothDetectorCallBack callBack);

    /**
     * 关闭扫描，并移除回调
     * 此方法调用请放在 {@link Activity#onDestroy()} 方法里执行
     * 与 {@link BluetoothDetectorHandler#startScan(BluetoothFilter, BluetoothDetectorCallBack)} 方法应是成对出现
     *
     * @param callBack
     */
    void stopScan(BluetoothDetectorCallBack callBack);
}