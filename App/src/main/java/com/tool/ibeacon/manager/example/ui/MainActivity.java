package com.tool.ibeacon.manager.example.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.logg.Logg;
import com.logg.config.LoggConfiguration;
import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.BluetoothDetector;
import com.tool.bluetooth.detector.BluetoothDetectorHandler;
import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.entity.BeaconDevice;
import com.tool.bluetooth.detector.receiver.BluetoothReceiver;
import com.tool.ibeacon.manager.example.BuildConfig;
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
        setContentView(R.layout.activity_scan);

        LoggConfiguration configuration = new LoggConfiguration.Buidler()
                .setDebug(BuildConfig.DEBUG_FLAG)
                .build();
        Logg.init(configuration);

        ListView deviceListView = (ListView) findViewById(R.id.list);
        mDeviceAdapter = new DeviceAdapter(this, R.layout.listitem_device,
                new ArrayList<BeaconDevice>());
        deviceListView.setAdapter(mDeviceAdapter);

        detector = BluetoothDetector.getInstance();
        BluetoothFilter filter = BluetoothFilter.builder()
                .debug(true)
                .build();
        detector.startScan(filter, MainActivity.this);

        registerReceiver(new BluetoothReceiver(), makeFilter());
    }

    private IntentFilter makeFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        return filter;
    }

    @Override
    public void onScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
        Log.e("onScanResult", device.getAddress() + " " + device.getName());

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
    protected void onDestroy() {
        super.onDestroy();
        detector.stopScan(this);
    }
}