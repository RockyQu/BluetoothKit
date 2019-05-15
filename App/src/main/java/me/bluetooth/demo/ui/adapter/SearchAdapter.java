package me.bluetooth.demo.ui.adapter;

import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import me.bluetooth.demo.R;
import me.bluetooth.demo.app.TimeUtils;
import me.bluetooth.detector.entity.BeaconDevice;
import me.bluetooth.detector.utils.LogDetector;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends BaseQuickAdapter<BeaconDevice, BaseViewHolder> {

    public SearchAdapter() {
        super(R.layout.item_search);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeaconDevice item) {
        // 设备名称
        AppCompatTextView deviceName = helper.getView(R.id.device_name);
        deviceName.setText(item.getDeviceName());
        // 设备地址
        AppCompatTextView deviceAddress = helper.getView(R.id.device_address);
        deviceAddress.setText(item.getBluetoothDevice().getAddress());
        // 信号强度
        AppCompatTextView rssi = helper.getView(R.id.device_rssi);
        rssi.setText(String.format(Locale.getDefault(), "RSSI:%d", item.getRssi()));
        // 接收时间
        AppCompatTextView lastupdated = helper.getView(R.id.device_time);
        lastupdated.setText(String.format(Locale.getDefault(), "Time:%s", TimeUtils.getTime(item.getLastUpdatedTimeMillis())));

        // 是否是标准蓝牙设备
        AppCompatTextView deviceIbeacon = helper.getView(R.id.device_ibeacon);
        if (item.getiBeacon() != null) {
            deviceIbeacon.setText(String.format(Locale.getDefault(), "This is iBeacon!\n%s", item.getiBeacon().toString()));
        } else {
            deviceIbeacon.setText("This is not iBeacon.");
        }

        // 附加信息
        AppCompatTextView deviceRecord = helper.getView(R.id.device_record);
        deviceRecord.setText(item.getScanRecordHexString());

        // 复制 UUID
        helper.addOnClickListener(R.id.UUID);
    }

    /**
     * add or update BluetoothDevice List
     *
     * @param newDevice  Scanned Bluetooth Device
     * @param rssi       RSSI
     * @param scanRecord advertise data
     * @return summary ex. "iBeacon:3 (Total:10)"
     */
    public String update(BluetoothDevice newDevice, int rssi, byte[] scanRecord) {
        if ((newDevice == null) || (newDevice.getAddress() == null)) {
            return "";
        }
        long now = System.currentTimeMillis();

        List<BeaconDevice> datas = getData();

        boolean contains = false;
        for (BeaconDevice device : datas) {
            if (newDevice.getAddress().equals(device.getBluetoothDevice().getAddress())) {
                contains = true;

                // update
                device.updateDevice(newDevice, rssi, scanRecord, now);
                break;
            }
        }
        if (!contains) {
            // add new BluetoothDevice
            datas.add(new BeaconDevice(newDevice, rssi, scanRecord, now));
        }

        // sort by RSSI
        Collections.sort(datas, new Comparator<BeaconDevice>() {
            @Override
            public int compare(BeaconDevice lhs, BeaconDevice rhs) {
                if (lhs.getRssi() == 0) {
                    return 1;
                } else if (rhs.getRssi() == 0) {
                    return -1;
                }
                if (lhs.getRssi() > rhs.getRssi()) {
                    return -1;
                } else if (lhs.getRssi() < rhs.getRssi()) {
                    return 1;
                }
                return 0;
            }
        });

        notifyDataSetChanged();

        // create summary
        int totalCount = 0;
        int iBeaconCount = 0;
        if (datas != null) {
            totalCount = datas.size();
            for (BeaconDevice device : datas) {
                if (device.getiBeacon() != null) {
                    iBeaconCount++;
                }
            }
        }
        String summary = "iBeacon:" + Integer.toString(iBeaconCount) + " (Total:"
                + Integer.toString(totalCount) + ")";

        return summary;
    }
}