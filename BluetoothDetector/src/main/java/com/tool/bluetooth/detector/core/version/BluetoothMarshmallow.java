package com.tool.bluetooth.detector.core.version;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.BluetoothDetectorHandler;

/**
 * Android 6.0 Marshmallow
 * <p>
 * 需要申请地理位置权限
 */
@TargetApi(Build.VERSION_CODES.M)
public class BluetoothMarshmallow extends BluetoothLollipop {

    @Override
    public void startScanInternal(BluetoothDetectorCallBack callBack) {
        super.startScanInternal(callBack);
    }

    @Override
    public void stopScanInternal(BluetoothDetectorCallBack callBack) {
        super.stopScanInternal(callBack);
    }

    @Override
    public void requestCheckEach(Context context, BluetoothDetectorHandler.CheckResponse response) {
        response.onNeedPermission(BluetoothDetectorHandler.LOCATION_PERMISSIONS);
        Log.e("abc","abc");
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_COARSE_LOCATION)) {
//                return;
//            }
//            ActivityCompat.requestPermissions((Activity)context,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                    888);
//            return;
//        }
        super.requestCheckEach(context, response);
    }
}