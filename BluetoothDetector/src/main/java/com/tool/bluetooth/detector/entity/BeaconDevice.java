package com.tool.bluetooth.detector.entity;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Arrays;

/**
 * ==================================================
 * 存放被扫描到的蓝牙设备的原始信息及附加信息
 * <p>
 * <p>
 * ==================================================
 */
public class BeaconDevice implements Parcelable {

    private static final String UNKNOWN = "Unknown";

    /**
     * Identifies the remote device
     */
    private BluetoothDevice bluetoothDevice;

    /**
     * The RSSI value for the remote device as reported by the Bluetooth hardware. 0 if no RSSI value is available.
     */
    private int rssi;

    /**
     * The content of the advertisement record offered by the remote device.
     */
    private byte[] scanRecord;

    /**
     * Device name
     */
    private String deviceName;

    /**
     * 解析后的设备相关数据
     */
    private IBeacon iBeacon;

    /**
     * 最后更新时间戳
     */
    private long lastUpdatedTimeMillis;

    public BeaconDevice(@NonNull BluetoothDevice bluetoothDevice, int rssi, @NonNull byte[] scanRecord, long lastUpdatedTimeMillis) {
        this.bluetoothDevice = bluetoothDevice;
        this.rssi = rssi;
        this.scanRecord = scanRecord;

        this.lastUpdatedTimeMillis = lastUpdatedTimeMillis;

        this.createIBeacon();
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public byte[] getScanRecord() {
        return scanRecord;
    }

    public void setScanRecord(byte[] scanRecord) {
        this.scanRecord = scanRecord;

        this.createIBeacon();
    }

    public String getDeviceName() {
        return (bluetoothDevice.getName() == null) || (bluetoothDevice.getName().length() == 0) ? UNKNOWN : bluetoothDevice.getName();
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public IBeacon getiBeacon() {
        return iBeacon;
    }

    public long getLastUpdatedTimeMillis() {
        return lastUpdatedTimeMillis;
    }

    public void setLastUpdatedTimeMillis(long lastUpdatedTimeMillis) {
        this.lastUpdatedTimeMillis = lastUpdatedTimeMillis;
    }

    /**
     * 更新当前设备的信息
     *
     * @param bluetoothDevice
     * @param rssi
     * @param scanRecord
     * @param lastUpdatedTimeMillis
     */
    public void updateDevice(@NonNull BluetoothDevice bluetoothDevice, int rssi, @NonNull byte[] scanRecord, long lastUpdatedTimeMillis) {
        this.bluetoothDevice = bluetoothDevice;
        this.rssi = rssi;
        this.scanRecord = scanRecord;

        this.lastUpdatedTimeMillis = lastUpdatedTimeMillis;

        this.createIBeacon();
    }

    /**
     * 创建IBeacon
     */
    private void createIBeacon() {
        if (scanRecord != null) {
            iBeacon = IBeacon.createIBeacon(scanRecord, rssi);
        }
    }

    public String getScanRecordHexString() {
        return BeaconDevice.asHex(scanRecord);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    @NonNull
    public static String asHex(@NonNull byte[] bytes) {
        if (bytes.length == 0) {
            return "";
        }

        // 生成字符串缓冲区的长度是字节数组的两倍
        StringBuffer sb = new StringBuffer(bytes.length * 2);

        // 重复该过程来获取字节数组元素的数量
        for (int index = 0; index < bytes.length; index++) {
            // 将字节值转换为自然数
            int bt = bytes[index] & 0xff;

            // 判断字节值是否为0x10或更小
            if (bt < 0x10) {
                // 当0x10或更小时，0被添加到字符串缓冲区
                sb.append("0");
            }

            // 将字节值转换为十六进制字符串并将其添加到字符串缓冲区
            sb.append(Integer.toHexString(bt).toUpperCase());
        }

        // 返回十六进制字符串
        return sb.toString();
    }

    @Override
    public String toString() {
        return "BeaconDevice{" +
                "bluetoothDevice=" + bluetoothDevice +
                ", rssi=" + rssi +
                ", scanRecord=" + Arrays.toString(scanRecord) +
                ", deviceName='" + deviceName + '\'' +
                ", iBeacon=" + iBeacon +
                ", lastUpdatedTimeMillis=" + lastUpdatedTimeMillis +
                '}';
    }

    private BeaconDevice(Parcel in) {
        this.bluetoothDevice = in.readParcelable(BluetoothDevice.class.getClassLoader());
        this.rssi = in.readInt();
        this.scanRecord = in.createByteArray();
        this.deviceName = in.readString();
        this.iBeacon = in.readParcelable(IBeacon.class.getClassLoader());
        this.lastUpdatedTimeMillis = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bluetoothDevice, flags);
        dest.writeInt(rssi);
        dest.writeByteArray(scanRecord);
        dest.writeString(deviceName);
        dest.writeParcelable(iBeacon, flags);
        dest.writeLong(lastUpdatedTimeMillis);
    }

    public static final Creator<BeaconDevice> CREATOR = new Creator<BeaconDevice>() {

        @Override
        public BeaconDevice createFromParcel(Parcel in) {
            return new BeaconDevice(in);
        }

        @Override
        public BeaconDevice[] newArray(int size) {
            return new BeaconDevice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
