package com.tool.ibeacon.manager.example.ui.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tool.bluetooth.detector.utils.DateUtil;
import com.tool.bluetooth.detector.entity.BeaconDevice;
import com.tool.common.utils.TimeUtils;
import com.tool.ibeacon.manager.example.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class DeviceAdapter extends ArrayAdapter<BeaconDevice> {
    private static final String PREFIX_RSSI = "RSSI:";
    private static final String PREFIX_LASTUPDATED = "Last Udpated:";
    private List<BeaconDevice> mList;
    private LayoutInflater mInflater;
    private int mResId;

    public DeviceAdapter(Context context, int resId, List<BeaconDevice> objects) {
        super(context, resId, objects);
        mResId = resId;
        mList = objects;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeaconDevice item = (BeaconDevice) getItem(position);

        if (convertView == null) {
            convertView = mInflater.inflate(mResId, null);
        }
        TextView name = (TextView) convertView.findViewById(R.id.device_name);
        name.setText(item.getDeviceName());
        TextView address = (TextView) convertView.findViewById(R.id.device_address);
        address.setText(item.getBluetoothDevice().getAddress());
        TextView rssi = (TextView) convertView.findViewById(R.id.device_rssi);
        rssi.setText(PREFIX_RSSI + Integer.toString(item.getRssi()));
        TextView lastupdated = (TextView) convertView.findViewById(R.id.device_lastupdated);
        lastupdated.setText(PREFIX_LASTUPDATED + TimeUtils.getTime(item.getLastUpdatedTimeMillis()));

        TextView ibeaconInfo = (TextView) convertView.findViewById(R.id.device_ibeacon_info);
        Resources res = convertView.getContext().getResources();
        if (item.getiBeacon() != null) {
            ibeaconInfo.setText(res.getString(R.string.label_ibeacon) + "\n"
                    + item.getiBeacon().toString());
        } else {
            ibeaconInfo.setText(res.getString(R.string.label_not_ibeacon));
        }
        TextView scanRecord = (TextView) convertView.findViewById(R.id.device_scanrecord);
        scanRecord.setText(item.getScanRecordHexString());

        return convertView;
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

        boolean contains = false;
        for (BeaconDevice device : mList) {
            if (newDevice.getAddress().equals(device.getBluetoothDevice().getAddress())) {
                contains = true;

                // update
                device.updateDevice(newDevice, rssi, scanRecord, now);
                break;
            }
        }
        if (!contains) {
            // add new BluetoothDevice
            mList.add(new BeaconDevice(newDevice, rssi, scanRecord, now));
        }

        // sort by RSSI
        Collections.sort(mList, new Comparator<BeaconDevice>() {
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
        if (mList != null) {
            totalCount = mList.size();
            for (BeaconDevice device : mList) {
                if (device.getiBeacon() != null) {
                    iBeaconCount++;
                }
            }
        }
        String summary = "iBeacon:" + Integer.toString(iBeaconCount) + " (Total:"
                + Integer.toString(totalCount) + ")";

        return summary;
    }

    public List<BeaconDevice> getList() {
        return mList;
    }
}
