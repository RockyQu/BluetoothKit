package com.tool.ibeacon.manager.example.ui.adapter;

import android.bluetooth.BluetoothDevice;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tool.bluetooth.detector.entity.BeaconDevice;
import com.tool.common.utils.TimeUtils;
import com.tool.ibeacon.manager.example.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<BeaconDevice, BaseViewHolder> {

    private static final String PREFIX_RSSI = "RSSI:";
    private static final String PREFIX_LASTUPDATED = "Last Udpated:";

    public SearchAdapter() {
        super(R.layout.item_search);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeaconDevice item) {
        TextView name = helper.getView(R.id.device_name);
        name.setText(item.getDeviceName());
        TextView address = helper.getView(R.id.device_address);
        address.setText(item.getBluetoothDevice().getAddress());
        TextView rssi = helper.getView(R.id.device_rssi);
        rssi.setText(PREFIX_RSSI + Integer.toString(item.getRssi()));
        TextView lastupdated = helper.getView(R.id.device_lastupdated);
        lastupdated.setText(PREFIX_LASTUPDATED + TimeUtils.getTime(item.getLastUpdatedTimeMillis()));

        TextView ibeaconInfo = helper.getView(R.id.device_ibeacon_info);
        if (item.getiBeacon() != null) {
            ibeaconInfo.setText("This is iBeacon!" + "\n"
                    + item.getiBeacon().toString());
        } else {
            ibeaconInfo.setText("This is not iBeacon.");
        }
        TextView scanRecord = helper.getView(R.id.device_scanrecord);
        scanRecord.setText(item.getScanRecordHexString());
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