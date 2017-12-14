package com.tool.bluetooth.detector;

import android.content.Context;

import com.tool.bluetooth.detector.config.BluetoothConfiguration;

/**
 * Android Bluetooth 4.0 Development Kit
 *
 * @see <a href="https://github.com/DesignQu/BluetoothKit"></a>
 */
public class BluetoothDetector implements BluetoothDetectorHandler {

    private BluetoothConfiguration configuration;

    private final static class HolderClass {
        private final static BluetoothDetectorHandler INSTANCE = new BluetoothDetector();
    }

    public static BluetoothDetectorHandler getDefault() {
        return BluetoothDetector.HolderClass.INSTANCE;
    }

    @Override
    public void startScan(BluetoothDetectorCallBack callBack) {

    }

    @Override
    public void stopScan(BluetoothDetectorCallBack callBack) {

    }

    @Override
    public void init(Context context, BluetoothConfiguration configuration) {
        this.configuration = configuration;

    }

//    public boolean openBluetoothSetting(Activity activity, int requestCode) {
//        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//        activity.startActivityForResult(intent, requestCode);
//        return false;
//    }


}