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
     * @param callBack
     */
    void startScan(BluetoothDetectorCallBack callBack);

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
    void requestCheckEach(Context context, CheckResponse response);

    int LOCATION_SERVICE = 26;
    int LOCATION_PERMISSIONS = 27;

    @IntDef({LOCATION_SERVICE, LOCATION_PERMISSIONS})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
    }

    interface CheckResponse {

        /**
         * 如果需要进行权限申请回调此方法
         */
        boolean onNeedPermission(@Type int type);

        /**
         * 没有需要处理的权限问题，在这里开启扫描
         */
        void onCheckSuccess();
    }
}