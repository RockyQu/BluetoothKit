package com.tool.ibeacon.manager.example.ui;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import com.logg.Logg;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.BluetoothDetector;
import com.tool.bluetooth.detector.BluetoothDetectorHandler;
import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.entity.BeaconDevice;
import com.tool.bluetooth.detector.utils.BluetoothUtils;
import com.tool.bluetooth.detector.utils.timer.TimerManager;
import com.tool.common.utils.PermissionUtils;
import com.tool.common.widget.Toaster;
import com.tool.ibeacon.manager.example.ui.adapter.DeviceAdapter;
import com.tool.ibeacon.manager.example.R;

import java.util.ArrayList;

/**
 * 扫描页面
 */
public class MainActivity extends Activity implements BluetoothDetectorCallBack {

    private DeviceAdapter mDeviceAdapter;

    private BluetoothDetectorHandler detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView deviceListView = (ListView) findViewById(R.id.list);
        mDeviceAdapter = new DeviceAdapter(this, R.layout.listitem_device,
                new ArrayList<BeaconDevice>());
        deviceListView.setAdapter(mDeviceAdapter);

        Logg.e("onCreate");
        // 检查蓝牙权限处理
        this.bluetoothScanCheck();

        TimerManager.getInstance().scheduleAtFrequency(3).setListener(new TimerManager.TimerListener() {
            @Override
            public void progress(long total, long progress) {
                Logg.e("run " + total + " progress " + progress);
            }

            @Override
            public void complete() {
                Logg.e("complete");
            }
        });
    }

    /**
     * 检查蓝牙权限处理
     */
    private boolean bluetoothScanCheck() {
        if (!BluetoothUtils.isSupportBluetooth()) {// 是否支持蓝牙
            Logg.e("设备没有蓝牙模块");
            showMessage("设备没有蓝牙模块");
            return false;
        }

        if (!BluetoothUtils.isOpenBluetooth() && !BluetoothUtils.openBluetooth()) {// 检查蓝牙是否开启，并尝试强制开启蓝牙
            Logg.e("开启蓝牙失败，请打开系统设置页面手动开启");
            showMessage("开启蓝牙失败，请打开系统设置页面手动开启");
            BluetoothUtils.openBluetooth(this, BluetoothUtils.REQUEST_CODE_BLUETOOTH);
            return false;
        }

        if (!BluetoothUtils.isOpenGPS(this)) {// 启动 GPS 定位服务
            Logg.e("启动 GPS 定位服务");
            showMessage("请开启 GPS 定位服务");
            BluetoothUtils.openGps(this, BluetoothUtils.REQUEST_CODE_GPS);
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// Android 6.0 以上版本，需要申请地理位置权限
            Logg.e("Android 6.0 以上版本，需要申请地理位置权限");
            PermissionUtils.location(new PermissionUtils.RequestPermission() {

                @Override
                public void onRequestPermissionSuccess() {
                    Logg.e("onRequestPermissionSuccess");
                    // 开启扫描
                    startScan();
                }

                @Override
                public void onRequestPermissionFailure() {
                    showMessage("请开启地理位置权限");
                    // 如果失败跳到到应用设置页面
                    BluetoothUtils.openPermissionsSetting(MainActivity.this);
                }
            }, new RxPermissions(this));
        }

        return true;
    }

    /**
     * 开启扫描
     */
    private void startScan() {
        Logg.e("startScan");
        detector = BluetoothDetector.getInstance();
        BluetoothFilter filter = BluetoothFilter.builder()
                .debug(true)
                .build();
        detector.startScan(filter, this);
    }

    /**
     * 停止扫描
     */
    private void stopScan() {
        if (detector != null) {
            detector.stopScan();
        }
    }

    @Override
    public void onScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
        Logg.e(device.getAddress() + " " + device.getName());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String summary = mDeviceAdapter.update(device, rssi, scanRecord);
                if (summary != null) {
                    getActionBar().setSubtitle(summary);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BluetoothUtils.REQUEST_CODE_BLUETOOTH:// 打开系统设置页面手动开启蓝牙，resultCode = 0 为用户拒绝，resultCode = -1 为用户同意
                if (resultCode == -1) {// 用户点击了同意
                    this.bluetoothScanCheck();
                } else {// 弹出系统 GPS 选择对话框时，用户点击了拒绝
                    showMessage("请转到设置页面开启蓝牙，否则无法使用此功能");
                }
                break;
            case BluetoothUtils.REQUEST_CODE_GPS:// 打开系统 GPS 设置页面手动开启 GPS
                if (resultCode == 0) {
                    this.bluetoothScanCheck();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showMessage(String message) {
        Toaster.with(this).setMessage(message).show();
    }

    @Override
    protected void onDestroy() {
        this.stopScan();

        super.onDestroy();
    }
}