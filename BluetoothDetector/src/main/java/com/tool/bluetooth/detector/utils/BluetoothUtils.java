package com.tool.bluetooth.detector.utils;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;

public class BluetoothUtils {

    public static final int REQUEST_CODE_GPS = 52;
    public static final int REQUEST_CODE_BLUETOOTH = 53;

    /**
     * 判断是否启动 GPS 定位服务
     *
     * @param context context
     * @return 是否启动定位服务
     */
    public static boolean isOpenGPS(Context context) {
        boolean isGps = false; //判断GPS定位是否启动
        if (context != null) {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (locationManager != null) {
                //通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
                isGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            } else {
                return isGps;
            }
        }
        return isGps;
    }

    /**
     * 打开 Gps 系统设置页面
     */
    public static void openGps(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent, REQUEST_CODE_GPS);
    }

    /**
     * 是否支持蓝牙
     */
    public static boolean isSupportBluetooth() {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    /**
     * 蓝牙是否开启
     *
     * @return
     */
    @RequiresPermission(Manifest.permission.BLUETOOTH)
    public static boolean isOpenBluetooth() {
        return BluetoothAdapter.getDefaultAdapter().isEnabled();
    }

    /**
     * 开启蓝牙
     *
     * @return true:开启成功
     */
    @RequiresPermission(Manifest.permission.BLUETOOTH_ADMIN)
    public static boolean openBluetooth() {
        return BluetoothAdapter.getDefaultAdapter().enable();
    }

    /**
     * 开启蓝牙，如果强制打开蓝牙失败，请再次调用这个方法转到系统设置页面开启
     *
     * @param activity
     * @param requestCode 请传入 {@link BluetoothUtils#REQUEST_CODE_BLUETOOTH} 常量
     * @return true:开启成功
     */
    public static void openBluetooth(Activity activity, int requestCode) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 关闭蓝牙
     *
     * @return true:关闭成功
     */
    @RequiresPermission(Manifest.permission.BLUETOOTH_ADMIN)
    public static boolean closeBluetooth() {
        return BluetoothAdapter.getDefaultAdapter().disable();
    }
}
