package com.tool.bluetooth.detector;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import com.tool.bluetooth.detector.config.BluetoothFilter;

public interface BluetoothDetectorHandler {

    /**
     * 开启扫描，并回调至 {@link BluetoothDetectorCallBack#onScan(BluetoothDevice, int, byte[])} 方法
     * 此方法调用请放在 {@link android.app.Activity#onCreate(Bundle)} 方法里执行
     * 与 {@link BluetoothDetectorHandler#stopScan(BluetoothDetectorCallBack)} 方法应是成对出现
     *
     * @param callBack
     */
    void startScan(BluetoothDetectorCallBack callBack);

    /**
     * 开启扫描，并回调至 {@link BluetoothDetectorCallBack#onScan(BluetoothDevice, int, byte[])} 方法
     * 此方法调用请放在 {@link android.app.Activity#onCreate(Bundle)} 方法里执行
     * 与 {@link BluetoothDetectorHandler#stopScan(BluetoothDetectorCallBack)} 方法应是成对出现
     *
     * @param configuration
     * @param callBack
     */
    void startScan(BluetoothFilter configuration, BluetoothDetectorCallBack callBack);

    /**
     * 关闭扫描，并移除回调
     * 此方法调用请放在 {@link Activity#onDestroy()} 方法里执行
     * 与 {@link BluetoothDetectorHandler#startScan(BluetoothDetectorCallBack)} 方法应是成对出现
     *
     * @param callBack
     */
    void stopScan(BluetoothDetectorCallBack callBack);

    /**
     * 检查 Android 各版本所需的权限处理，在开启扫描前请调用此方法
     * 你可以配合目前流行的 RxPermissions 权限处理库
     * 该库已被集成至 <a href="https://github.com/DesignQu/MVPFrames"></a> MVP架构中，实现一行代码权限处理
     * <p>
     * 关于Bluetooth 4.0 在 Android 各版本的差异化简要说明
     *
     * @param response
     */
    void checkEach(CheckResponse response);

    interface CheckResponse {

        /**
         * 权限申请失败执行的方法
         * 包括：用户拒绝、用户选择了禁止弹出、当批量申请只要有一个拒绝就会执行该方法
         */
        void onRequestPermissionFailure();

        /**
         * 没有需要处理的权限问题，在这里开启扫描
         */
        void onCheckSuccess();
    }
}