package com.tool.bluetooth.detector.core.version;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.BluetoothDetectorHandler;
import com.tool.bluetooth.detector.utils.Utils;

/**
 * Android 7.0 Nougat
 * <p>
 * 需要检测设备是否打开位置信息
 */
@TargetApi(Build.VERSION_CODES.N)
public class BluetoothNougat extends BluetoothMarshmallow {

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
        Log.e("requestCheckEach", "1");
        if (!Utils.isOpenGPS(context)) {
            Log.e("requestCheckEach", "2");
            response.onNeedPermission(BluetoothDetectorHandler.LOCATION_SERVICE);
        }else{
            Log.e("requestCheckEach", "3");
        }

        super.requestCheckEach(context, response);
    }
}