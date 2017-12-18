package com.tool.bluetooth.detector.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

public class Utils {

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

    public static int GPS_REQUEST_CODE = 52;

    /**
     * 打开 Gps 系统设置页面
     */
    public static void openGps(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent, GPS_REQUEST_CODE);
    }
}
