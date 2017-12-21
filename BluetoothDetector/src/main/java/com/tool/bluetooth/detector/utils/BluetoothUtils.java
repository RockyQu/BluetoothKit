package com.tool.bluetooth.detector.utils;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;

public class BluetoothUtils {

    public static final int REQUEST_CODE_GPS = 52;
    public static final int REQUEST_CODE_BLUETOOTH = 53;
    public static final int REQUEST_CODE_PERMISSIONS = 54;

    /**
     * 判断是否启动 GPS 定位服务
     *
     * @param context context
     * @return 是否启动定位服务
     */
    public static boolean isOpenGPS(Context context) {
        boolean isGps = false;
        if (context != null) {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (locationManager != null) {
                isGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            } else {
                return isGps;
            }
        }
        return isGps;
    }

    /**
     * 打开 Gps 系统设置页面
     *
     * @param activity
     * @param requestCode 请传入 {@link BluetoothUtils#REQUEST_CODE_GPS} 常量
     */
    public static void openGps(Activity activity, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivityForResult(intent, requestCode);
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
     * 强制开启蓝牙
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

    /**
     * 打开系统权限设备页面
     *
     * @param activity
     * @param requestCode 请传入 {@link BluetoothUtils#REQUEST_CODE_PERMISSIONS} 常量
     */
    public static void openPermissionsSetting(Activity activity, int requestCode) {
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, requestCode);
    }

}
