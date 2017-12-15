package com.tool.bluetooth.detector.config;

import java.util.UUID;

/**
 * 参数配置
 */
public class BluetoothFilter {

    private String[] serviceUUIDs = null;
    private String[] deviceNames = null;
    private String[] deviceMacs = null;

    private boolean debug;

    public BluetoothFilter(Builder builder) {
        this.serviceUUIDs = builder.serviceUUIDs;
        this.deviceNames = builder.deviceNames;
        this.deviceMacs = builder.deviceMacs;

        this.debug = builder.debug;
    }

    public UUID[] getServiceUUIDs() {
        if (serviceUUIDs != null && serviceUUIDs.length != 0) {
            UUID[] uuids = new UUID[serviceUUIDs.length];
            for (int i = 0; i < serviceUUIDs.length; i++) {
                uuids[i] = UUID.fromString(serviceUUIDs[i]);
            }

            return uuids;
        }

        return null;
    }

    public String[] getDeviceNames() {
        return deviceNames;
    }

    public String[] getDeviceMacs() {
        return deviceMacs;
    }

    public boolean isDebug() {
        return debug;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        // 过滤指定服务的设备
        private String[] serviceUUIDs = null;
        // 过滤指定设备的名称
        private String[] deviceNames = null;
        // 过滤指定设备的地址
        private String[] deviceMacs = null;

        // 调试模式
        private boolean debug = false;

        private Builder() {
            ;
        }

        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder serviceUUIDs(String[] serviceUUIDs) {
            this.serviceUUIDs = serviceUUIDs;
            return this;
        }

        public Builder deviceNames(String[] deviceNames) {
            this.deviceNames = deviceNames;
            return this;
        }

        public Builder deviceMacs(String[] deviceMacs) {
            this.deviceMacs = deviceMacs;
            return this;
        }

        public BluetoothFilter build() {
            return new BluetoothFilter(this);
        }
    }
}