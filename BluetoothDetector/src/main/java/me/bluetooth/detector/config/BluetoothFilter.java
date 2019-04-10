package me.bluetooth.detector.config;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.annotation.IntDef;

/**
 * 参数配置
 */
public class BluetoothFilter {

    private List<String> serviceUUIDs;
    private List<String> deviceNames;
    private List<String> deviceAddresses;

    public static final int SCAN_MODE_LOW_POWER = 0;
    public static final int SCAN_MODE_BALANCED = 1;
    public static final int SCAN_MODE_LOW_LATENCY = 2;

    @IntDef({BluetoothFilter.SCAN_MODE_LOW_POWER, BluetoothFilter.SCAN_MODE_BALANCED, BluetoothFilter.SCAN_MODE_LOW_LATENCY})
    @Retention(RetentionPolicy.SOURCE)
    @interface ScanMode {
    }

    @ScanMode
    private int scanMode;

    private boolean debug;

    public BluetoothFilter(Builder builder) {
        this.serviceUUIDs = builder.serviceUUIDs;
        this.deviceNames = builder.deviceNames;
        this.deviceAddresses = builder.deviceAddresses;

        this.scanMode = builder.scanMode;

        this.debug = builder.debug;
    }

    public List<UUID> getServiceUUIDs() {
        List<UUID> uuids = new ArrayList<>();
        for (String uuid : serviceUUIDs) {
            uuids.add(UUID.fromString(uuid));
        }

        return uuids;
    }

    public List<String> getDeviceNames() {
        return deviceNames;
    }

    public List<String> getDeviceAddresses() {
        return deviceAddresses;
    }

    public int getScanMode() {
        return scanMode;
    }

    public boolean isDebug() {
        return debug;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        // 指定被扫描服务的设备
        private List<String> serviceUUIDs = new ArrayList<>();
        // 指定被扫描设备的名称
        private List<String> deviceNames = new ArrayList<>();
        // 指定被扫描设备的地址
        private List<String> deviceAddresses = new ArrayList<>();

        // 搜索频率，这个参数只在 Android 5.0 以上有效果
        // 三个参数依次会越来越耗电，但扫描结果会越来越快
        @ScanMode
        private int scanMode = BluetoothFilter.SCAN_MODE_LOW_LATENCY;

        // 调试模式
        private boolean debug = false;

        private Builder() {
            ;
        }

        public Builder addSetserviceUUID(String serviceUUID) {
            this.serviceUUIDs.add(serviceUUID);
            return this;
        }

        public Builder addServiceUUIDs(List<String> serviceUUIDs) {
            this.serviceUUIDs.addAll(serviceUUIDs);
            return this;
        }

        public Builder setServiceUUIDs(List<String> serviceUUIDs) {
            this.serviceUUIDs = serviceUUIDs;
            return this;
        }

        public Builder addDeviceName(String deviceName) {
            this.deviceNames.add(deviceName);
            return this;
        }

        public Builder addDeviceNames(List<String> deviceNames) {
            this.deviceNames.addAll(deviceNames);
            return this;
        }

        public Builder setDeviceNames(List<String> deviceNames) {
            this.deviceNames = deviceNames;
            return this;
        }

        public Builder addDeviceAddress(String deviceAddress) {
            this.deviceAddresses.add(deviceAddress);
            return this;
        }

        public Builder addDeviceAddresses(List<String> deviceAddresses) {
            this.deviceAddresses.addAll(deviceAddresses);
            return this;
        }

        public Builder setDeviceAddresses(List<String> deviceAddresses) {
            this.deviceAddresses = deviceAddresses;
            return this;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public Builder scanMode(@ScanMode int scanMode) {
            this.scanMode = scanMode;
            return this;
        }

        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public BluetoothFilter build() {
            return new BluetoothFilter(this);
        }
    }
}