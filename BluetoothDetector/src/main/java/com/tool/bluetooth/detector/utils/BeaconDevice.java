package com.tool.bluetooth.detector.utils;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;

import com.tool.bluetooth.detector.IBeacon;

/**
 * ==================================================
 * 存放被扫描到的蓝牙设备的原始信息及附加信息
 * <p>
 * <p>
 * ==================================================
 */
public class BeaconDevice {

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
    private byte[] mScanRecord;

    /**
     * Device name
     */
    private String deviceName;

    /**
     * 解析后的设备相关数据
     */
    private IBeacon mIBeacon;

    /**
     * 最后更新时间
     */
    private long lastUpdatedTimeMillis;

    public BeaconDevice(BluetoothDevice device, int rssi, byte[] scanRecord, long now) {
        lastUpdatedTimeMillis = now;
        bluetoothDevice = device;
        deviceName = device.getName();
        if ((deviceName == null) || (deviceName.length() == 0)) {
            deviceName = UNKNOWN;
        }
        this.rssi = rssi;
        mScanRecord = scanRecord;
        checkIBeacon();
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public void updateDevice(BluetoothDevice bluetoothDevice, int rssi, byte[] scanRecord) {

    }

    private void checkIBeacon() {
        if (mScanRecord != null) {
            mIBeacon = IBeacon.fromScanData(mScanRecord, rssi);
        }
    }

    public int getRssi() {
        return this.rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public long getLastUpdatedMs() {
        return lastUpdatedTimeMillis;
    }

    public void setLastUpdatedMs(long lastUpdatedMs) {
        lastUpdatedTimeMillis = lastUpdatedMs;
    }

    public byte[] getScanRecord() {
        return mScanRecord;
    }

    public String getScanRecordHexString() {
        return BeaconDevice.asHex(mScanRecord);
    }

    public void setScanRecord(byte[] scanRecord) {
        mScanRecord = scanRecord;
        checkIBeacon();
    }

    public IBeacon getIBeacon() {
        return mIBeacon;
    }

    public String getDisplayName() {
        return deviceName;
    }

    public void setDisplayName(String displayName) {
        deviceName = displayName;
    }

    public String toCsv() {
        StringBuilder sb = new StringBuilder();
        // DisplayName,MAC Addr,RSSI,Last Updated,iBeacon flag,Proximity UUID,major,minor,TxPower
        sb.append(deviceName).append(",");
        sb.append(bluetoothDevice.getAddress()).append(",");
        sb.append(this.rssi).append(",");
        sb.append(DateUtil.get_yyyyMMddHHmmssSSS(lastUpdatedTimeMillis)).append(",");
        if (mIBeacon == null) {
            sb.append("false,,0,0,0");
        } else {
            sb.append("true").append(",");
            sb.append(mIBeacon.toCsv());
        }
        return sb.toString();
    }

    /**
     * バイト配列を16進数の文字列に変換する。 http://d.hatena.ne.jp/winebarrel/20041012/p1
     *
     * @param bytes バイト配列
     * @return 16進数の文字列
     */
    @SuppressLint("DefaultLocale")
    public static String asHex(byte bytes[]) {
        if ((bytes == null) || (bytes.length == 0)) {
            return "";
        }

        // バイト配列の２倍の長さの文字列バッファを生成。
        StringBuffer sb = new StringBuffer(bytes.length * 2);

        // バイト配列の要素数分、処理を繰り返す。
        for (int index = 0; index < bytes.length; index++) {
            // バイト値を自然数に変換。
            int bt = bytes[index] & 0xff;

            // バイト値が0x10以下か判定。
            if (bt < 0x10) {
                // 0x10以下の場合、文字列バッファに0を追加。
                sb.append("0");
            }

            // バイト値を16進数の文字列に変換して、文字列バッファに追加。
            sb.append(Integer.toHexString(bt).toUpperCase());
        }

        /// 16進数の文字列を返す。
        return sb.toString();
    }
}
