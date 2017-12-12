package com.tool.bluetooth.detector;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import com.tool.bluetooth.detector.config.BluetoothConfiguration;

/**
 *
 */
public class BluetoothDetectorManager implements IBluetoothDetectorManager {

    private BluetoothConfiguration configuration;

    private final static class HolderClass {
        private final static IBluetoothDetectorManager INSTANCE = new BluetoothDetectorManager();
    }

    public static IBluetoothDetectorManager getInstance() {
        return BluetoothDetectorManager.HolderClass.INSTANCE;
    }

    @Override
    public void startScan(BluetoothDetectorCallBack callBack) {

    }

    @Override
    public void stopScan(BluetoothDetectorCallBack callBack) {

    }

    @Override
    public void setConfiguration(BluetoothConfiguration configuration) {
        this.configuration = configuration;
    }

    public boolean openBluetoothSetting(Activity activity, int requestCode) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(intent, requestCode);
        return false;
    }


}