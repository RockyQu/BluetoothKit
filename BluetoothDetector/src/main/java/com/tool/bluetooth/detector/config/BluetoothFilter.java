package com.tool.bluetooth.detector.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 参数配置
 */
public class BluetoothFilter {

    // 过滤指定服务的设备
    private List<String> serviceUUIDs = null;
    // 过滤指定设备的名称
    private List<String> deviceNames = null;
    // 过滤指定设备的地址
    private List<String> deviceAddresses = null;

    // 调试模式
    private boolean debug;

    public BluetoothFilter(Builder builder) {
        this.serviceUUIDs = builder.serviceUUIDs;
        this.deviceNames = builder.deviceNames;
        this.deviceAddresses = builder.deviceAddresses;

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

    public boolean isDebug() {
        return debug;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<String> serviceUUIDs = new ArrayList<>();
        private List<String> deviceNames = new ArrayList<>();
        private List<String> deviceAddresses = new ArrayList<>();

        private boolean debug = false;

        private Builder() {
            ;
        }

        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
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

        public BluetoothFilter build() {
            return new BluetoothFilter(this);
        }
    }
}