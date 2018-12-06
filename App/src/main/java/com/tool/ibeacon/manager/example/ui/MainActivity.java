package com.tool.ibeacon.manager.example.ui;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.BluetoothDetector;
import com.tool.bluetooth.detector.BluetoothDetectorHandler;
import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.utils.BluetoothUtils;
import com.tool.ibeacon.manager.example.R;
import com.tool.ibeacon.manager.example.app.PermissionUtils;
import com.tool.ibeacon.manager.example.ui.adapter.SearchAdapter;

/**
 * 扫描页面
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private BluetoothDetectorHandler detector;

    RecyclerView recyclerView;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new SearchAdapter());

        Log.e(TAG, "onCreate");
        // 检查蓝牙权限处理
        this.bluetoothScanCheck();
    }

    /**
     * 检查蓝牙权限处理
     */
    private boolean bluetoothScanCheck() {
        if (!BluetoothUtils.isSupportBluetooth()) {// 是否支持蓝牙
            Log.e(TAG, "设备没有蓝牙模块");
            showMessage("设备没有蓝牙模块");
            return false;
        }

        if (!BluetoothUtils.isOpenBluetooth() && !BluetoothUtils.openBluetooth()) {// 检查蓝牙是否开启，并尝试强制开启蓝牙
            Log.e(TAG, "开启蓝牙失败，请打开系统设置页面手动开启");
            showMessage("开启蓝牙失败，请打开系统设置页面手动开启");
            BluetoothUtils.openBluetooth(this, BluetoothUtils.REQUEST_CODE_BLUETOOTH);
            return false;
        }

        if (!BluetoothUtils.isOpenGPS(this)) {// 启动 GPS 定位服务
            Log.e(TAG, "启动 GPS 定位服务");
            showMessage("请开启 GPS 定位服务");
            BluetoothUtils.openGps(this, BluetoothUtils.REQUEST_CODE_GPS);
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// Android 6.0 以上版本，需要申请地理位置权限
            Log.e(TAG, "Android 6.0 以上版本，需要申请地理位置权限");
            PermissionUtils.location(new PermissionUtils.RequestPermission() {

                @Override
                public void onRequestPermissionSuccess() {
                    Log.e(TAG, "onRequestPermissionSuccess");
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
        Log.e(TAG, "startScan");
        detector = BluetoothDetector.getInstance();

        // 配置一些过滤条件
        BluetoothFilter filter = BluetoothFilter.builder()
                .addDeviceAddress("20:CD:39:B0:7A:7C")// 55
                .addDeviceAddress("20:CD:39:B0:7A:59")// 73
                .addDeviceAddress("20:CD:39:B0:7A:62")// 78
                .addDeviceAddress("20:CD:39:B0:71:4B")// 94
                .addDeviceAddress("20:CD:39:B0:6E:40")// 148
                .addDeviceAddress("20:CD:39:B0:7A:4A")// 162
                .debug(true)
                .build();

        detector.startScan(filter, new BluetoothDetectorCallBack() {

            @Override
            public void onScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
                Log.e(TAG, device.getAddress() + " " + device.getName());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String summary = adapter.update(device, rssi, scanRecord);
                        if (summary != null) {
//                            Logg.e("summary " + summary);
                        }
                    }
                });
            }
        });
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        this.stopScan();

        super.onDestroy();
    }
}