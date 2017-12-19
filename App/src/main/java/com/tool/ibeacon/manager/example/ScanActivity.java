package com.tool.ibeacon.manager.example;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.logg.Logg;
import com.logg.config.LoggConfiguration;
import com.tool.bluetooth.detector.BluetoothDetectorCallBack;
import com.tool.bluetooth.detector.BluetoothDetector;
import com.tool.bluetooth.detector.BluetoothDetectorHandler;
import com.tool.bluetooth.detector.config.BluetoothFilter;
import com.tool.bluetooth.detector.receiver.BluetoothReceiver;
import com.tool.bluetooth.detector.utils.BluetoothUtils;

/**
 *
 */
public class ScanActivity extends Activity implements BluetoothDetectorCallBack
//        , BluetoothAdapter.LeScanCallback
{

//    private BluetoothAdapter mBTAdapter;
//    private DeviceAdapter mDeviceAdapter;
//    private boolean mIsScanning;

    private BluetoothDetectorHandler detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        LoggConfiguration configuration = new LoggConfiguration.Buidler()
                .setDebug(BuildConfig.DEBUG_FLAG)
//                .setTag("test")// 自定义全局Tag
                .build();
        Logg.init(configuration);

//        init();


        BluetoothFilter filter = BluetoothFilter.builder()
                .debug(true)
                .build();
        detector.startScan(filter, ScanActivity.this);

        registerReceiver(new BluetoothReceiver(), makeFilter());
    }

    private IntentFilter makeFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        return filter;
    }

    @Override
    public void onScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        Log.e("onScanResult", device.getAddress() + " " + device.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detector.stopScan(this);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if ((mBTAdapter != null) && (!mBTAdapter.isEnabled())) {
//            Toast.makeText(this, R.string.bt_not_enabled, Toast.LENGTH_SHORT).show();
//            invalidateOptionsMenu();
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        stopScan();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        if (mIsScanning) {
//            menu.findItem(R.id.action_scan).setVisible(false);
//            menu.findItem(R.id.action_stop).setVisible(true);
//        } else {
//            menu.findItem(R.id.action_scan).setEnabled(true);
//            menu.findItem(R.id.action_scan).setVisible(true);
//            menu.findItem(R.id.action_stop).setVisible(false);
//        }
//        if ((mBTAdapter == null) || (!mBTAdapter.isEnabled())) {
//            menu.findItem(R.id.action_scan).setEnabled(false);
//        }
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//        int itemId = item.getItemId();
//        if (itemId == android.R.id.home) {
//            // ignore
//            return true;
//        } else if (itemId == R.id.action_scan) {
//            Logg.e("bbbbbbbbbb");
//            startScan();
//            return true;
//        } else if (itemId == R.id.action_stop) {
//            stopScan();
//            return true;
//        } else if (itemId == R.id.action_clear) {
//            if ((mDeviceAdapter != null) && (mDeviceAdapter.getCount() > 0)) {
//                mDeviceAdapter.clear();
//                mDeviceAdapter.notifyDataSetChanged();
//                getActionBar().setSubtitle("");
//            }
//            return true;
//        }
//        return super.onMenuItemSelected(featureId, item);
//    }
//
//    @Override
//    public void onLeScan(final BluetoothDevice newDeivce, final int newRssi,
//                         final byte[] newScanRecord) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                String summary = mDeviceAdapter.update(newDeivce, newRssi, newScanRecord);
//                if (summary != null) {
//                    getActionBar().setSubtitle(summary);
//                }
//            }
//        });
//    }
//
//    private void init() {
//        // BLE check
//        if (!BleUtil.isBLESupported(this)) {
//            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
//            finish();
//            return;
//        }
//
//        // BT check
//        BluetoothManager manager = BleUtil.getManager(this);
//        if (manager != null) {
//            mBTAdapter = manager.getAdapter();
//        }
//        if (mBTAdapter == null) {
//            Toast.makeText(this, R.string.bt_not_supported, Toast.LENGTH_SHORT).show();
//            finish();
//            return;
//        }
//
//        // init listview
//        ListView deviceListView = (ListView) findViewById(R.id.list);
//        mDeviceAdapter = new DeviceAdapter(this, R.layout.listitem_device,
//                new ArrayList<BeaconDevice>());
//        deviceListView.setAdapter(mDeviceAdapter);
//        stopScan();
//    }
//
//    private void startScan() {
//        if ((mBTAdapter != null) && (!mIsScanning)) {
//            mBTAdapter.startLeScan(this);
//            mIsScanning = true;
//            setProgressBarIndeterminateVisibility(true);
//            invalidateOptionsMenu();
//        }
//    }
//
//    private void stopScan() {
//        if (mBTAdapter != null) {
//            mBTAdapter.stopLeScan(this);
//        }
//        mIsScanning = false;
//        setProgressBarIndeterminateVisibility(false);
//        invalidateOptionsMenu();
//    }

}
